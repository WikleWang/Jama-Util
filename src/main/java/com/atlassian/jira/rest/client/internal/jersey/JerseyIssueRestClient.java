package com.atlassian.jira.rest.client.internal.jersey;

import com.atlassian.jira.rest.client.*;
import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.domain.input.*;
import com.atlassian.jira.rest.client.internal.json.*;
import com.atlassian.jira.rest.client.internal.json.gen.*;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.header.FormDataContentDisposition.FormDataContentDispositionBuilder;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.MultiPartMediaTypes;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.annotation.Nullable;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.concurrent.Callable;

public class JerseyIssueRestClient extends AbstractJerseyRestClient implements IssueRestClient {
    private static final String FILE_ATTACHMENT_CONTROL_NAME = "file";
    private static final EnumSet<Expandos> DEFAULT_EXPANDS;
    private static final String DEFAULT_FIELDS = "project,issuetype,summary,created,updated,status";
    private static final Function<Expandos, String> EXPANDO_TO_PARAM;
    private final SessionRestClient sessionRestClient;
    private final MetadataRestClient metadataRestClient;
    private final IssueJsonParser issueParser = new IssueJsonParser();
    private final BasicIssueJsonParser basicIssueParser = new BasicIssueJsonParser();
    private final BulkIssueJsonParser bulkIssueParser = new BulkIssueJsonParser();
    private final JsonObjectParser<Watchers> watchersParser = WatchersJsonParserBuilder.createWatchersParser();
    private final TransitionJsonParser transitionJsonParser = new TransitionJsonParser();
    private final JsonObjectParser<Transition> transitionJsonParserV5 = new TransitionJsonParserV5();
    private final VotesJsonParser votesJsonParser = new VotesJsonParser();
    private final CreateIssueMetadataJsonParser createIssueMetadataJsonParser = new CreateIssueMetadataJsonParser();
    private final EditIssueMetadataJsonParser editIssueMetadataJsonParser = new EditIssueMetadataJsonParser();
    private ServerInfo serverInfo;

    public JerseyIssueRestClient(URI baseUri, ApacheHttpClient client, SessionRestClient sessionRestClient, MetadataRestClient metadataRestClient) {
        super(baseUri, client);
        this.sessionRestClient = sessionRestClient;
        this.metadataRestClient = metadataRestClient;
    }

    private synchronized ServerInfo getVersionInfo(ProgressMonitor progressMonitor) {
        if (this.serverInfo == null) {
            this.serverInfo = this.metadataRestClient.getServerInfo(progressMonitor);
        }

        return this.serverInfo;
    }

    public Watchers getWatchers(String issueKey, ProgressMonitor progressMonitor) {
        return (Watchers) this.getAndParse(URI.create(baseUri + "/issue/" + issueKey + "/watchers"), this.watchersParser, progressMonitor);
    }

    public Votes getVotes(String issueKey, ProgressMonitor progressMonitor) {
        return (Votes) this.getAndParse(URI.create(baseUri + "/issue/" + issueKey + "/votes"), this.votesJsonParser, progressMonitor);
    }

    public Issue getIssue(String issueKey, ProgressMonitor progressMonitor) {
        return this.getIssue(issueKey, Collections.<Expandos>emptyList(), progressMonitor);
    }

    public Issue getIssue(String issueKey, Iterable<Expandos> expand, ProgressMonitor progressMonitor) {
        return (Issue) this.getIssue(issueKey, null, expand, progressMonitor);
    }


    public Issue getIssue(String issueKey, String fields, ProgressMonitor progressMonitor) {
        Set<String> fieldSet = new HashSet<>();
        if (fields == null) {
            fields = DEFAULT_FIELDS;
        } else {
            fields += "," + DEFAULT_FIELDS;
        }
        fieldSet.addAll(Arrays.asList(fields.split(",")));
        return (Issue) this.getIssue(issueKey, fieldSet, null, progressMonitor);
    }

    public Issue getIssue(String issueKey, Set<String> fields, Iterable<Expandos> expand, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(this.baseUri);
        UriBuilder issue = uriBuilder.path("issue").path(issueKey);
        // fields有强制要求，必须有DEFAULT_FIELDS
        if (fields != null) {
            fields.addAll(Arrays.asList(DEFAULT_FIELDS.split(",")));
            StringBuffer fieldNames = new StringBuffer();
            fields.stream().forEach(field -> fieldNames.append(fieldNames.length() > 0 ? "," + field : field));
            issue.queryParam("fields", fieldNames);
        } else {
            fields = new HashSet<>(Arrays.asList(DEFAULT_FIELDS.split(",")));
        }

        if (expand != null) {
            Iterable<Expandos> expands = Iterables.concat(DEFAULT_EXPANDS, expand);
            issue.queryParam("expand", new Object[]{Joiner.on(',').join(Iterables.transform(expands, EXPANDO_TO_PARAM))});
        }
        return (Issue) this.getAndParse(uriBuilder.build(new Object[0]), this.issueParser, progressMonitor);
    }

    public Iterable<Transition> getTransitions(final String issueKey, ProgressMonitor progressMonitor) {
        return this.getTransitions(URI.create(baseUri + "/issue/" + issueKey + "/transitions?expand=transitions.fields"), progressMonitor);
    }

    private Iterable<Transition> getTransitions(final URI transitionsUri, ProgressMonitor progressMonitor) {
        return (Iterable) this.invoke(new Callable<Iterable<Transition>>() {
            public Iterable<Transition> call() throws Exception {
                WebResource transitionsResource = JerseyIssueRestClient.this.client.resource(transitionsUri);
                JSONObject jsonObject = (JSONObject) transitionsResource.get(JSONObject.class);
                if (jsonObject.has("transitions")) {
                    return JsonParseUtil.parseJsonArray(jsonObject.getJSONArray("transitions"), JerseyIssueRestClient.this.transitionJsonParserV5);
                } else {
                    Collection<Transition> transitions = new ArrayList(jsonObject.length());
                    Iterator iterator = jsonObject.keys();

                    while (iterator.hasNext()) {
                        String key = (String) iterator.next();

                        try {
                            int id = Integer.parseInt(key);
                            Transition transition = JerseyIssueRestClient.this.transitionJsonParser.parse(jsonObject.getJSONObject(key), id);
                            transitions.add(transition);
                        } catch (JSONException var8) {
                            throw new RestClientException(var8);
                        } catch (NumberFormatException var9) {
                            throw new RestClientException("Transition id should be an integer, but found [" + key + "]", var9);
                        }
                    }

                    return transitions;
                }
            }
        });
    }

    public Iterable<IssueLink> getIssueLinks(String issueKey, ProgressMonitor progressMonitor) {
        return this.getIssue(issueKey, "issuelinks", progressMonitor).getIssueLinks();
    }


    private void transition(final URI transitionsUri, final TransitionInput transitionInput, final ProgressMonitor progressMonitor) {
        final int buildNumber = this.getVersionInfo(progressMonitor).getBuildNumber();
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                JSONObject jsonObject = new JSONObject();
                if (buildNumber >= 700) {
                    jsonObject.put("transition", (new JSONObject()).put("id", transitionInput.getId()));
                } else {
                    jsonObject.put("transition", transitionInput.getId());
                }

                if (transitionInput.getComment() != null) {
                    if (buildNumber >= 700) {
                        jsonObject.put("update", (new JSONObject()).put("comment", (new JSONArray()).put((new JSONObject()).put("add", (new CommentJsonGenerator(JerseyIssueRestClient.this.getVersionInfo(progressMonitor))).generate(transitionInput.getComment())))));
                    } else {
                        jsonObject.put("comment", (new CommentJsonGenerator(JerseyIssueRestClient.this.getVersionInfo(progressMonitor))).generate(transitionInput.getComment()));
                    }
                }

                Iterable<FieldInput> fields = transitionInput.getFields();
                JSONObject fieldsJs = (new IssueUpdateJsonGenerator()).generate(fields);
                if (fieldsJs.keys().hasNext()) {
                    jsonObject.put("fields", fieldsJs);
                }

                WebResource issueResource = JerseyIssueRestClient.this.client.resource(transitionsUri);
                issueResource.post(jsonObject);
                return null;
            }
        });
    }

    public void transition(Issue issue, TransitionInput transitionInput, ProgressMonitor progressMonitor) {
        this.transition(issue.getTransitionsUri(), transitionInput, progressMonitor);
    }

    public void transition(String issueKey, Integer transitionId, ProgressMonitor progressMonitor) {
        this.transition(URI.create(baseUri + "/issue/" + issueKey + "/transitions"), new TransitionInput(transitionId), progressMonitor);
    }

    public void transition(String issueKey, String transitionName, ProgressMonitor progressMonitor) {
        Iterable<Transition> transitions = this.getTransitions(issueKey, progressMonitor);
        Iterator<Transition> iterator = transitions.iterator();
        Integer transitionId = null;
        while (iterator.hasNext()) {
            Transition transition = iterator.next();
            String name = transition.getName();
            if (name.equals(transitionName)) {
                transitionId = transition.getId();
                break;
            }
        }
        if (transitionId == null) {
            throw new RestClientException(Arrays.asList("Could not find transition id with name : " + transitionName));
        } else {
            this.transition(URI.create(baseUri + "/issue/" + issueKey + "/transitions"), new TransitionInput(transitionId), progressMonitor);
        }
    }

    public void updateByKey(final String issueKey, final Iterable<FieldInput> fields, ProgressMonitor progressMonitor) {
        this.update(issueKey, fields, progressMonitor);
    }

    public void updateById(final Long issueId, final Iterable<FieldInput> fields, ProgressMonitor progressMonitor) {
        this.update(issueId, fields, progressMonitor);
    }

    private void update(final Object issueId, final Iterable<FieldInput> fields, ProgressMonitor progressMonitor) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                JSONObject jsonObject = new JSONObject();
                WebResource issueResource = JerseyIssueRestClient.this.client.resource(baseUri + "/issue/" + issueId);
                JSONObject fieldsJs = (new IssueUpdateJsonGenerator()).generate(fields);
                if (fieldsJs.keys().hasNext()) {
                    jsonObject.put("fields", fieldsJs);
                }

                issueResource.put(jsonObject);
                return null;
            }
        });
    }

    private void removeIssue(URI issueUri, boolean deleteSubtasks, ProgressMonitor progressMonitor) {
        if (deleteSubtasks) {
            this.delete(UriBuilder.fromUri(issueUri).queryParam("deleteSubtasks", new Object[]{"true"}).build(new Object[0]), progressMonitor);
        } else {
            this.delete(issueUri, progressMonitor);
        }

    }


    public void removeIssue(Long issueId, boolean deleteSubtasks, ProgressMonitor progressMonitor) {
        this.removeIssue(Long.toString(issueId), deleteSubtasks, progressMonitor);
    }

    public void removeIssue(String issueKey, boolean deleteSubtasks, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(this.baseUri).path("issue").path(issueKey);
        if (deleteSubtasks) {
            uriBuilder.queryParam("deleteSubtasks", new Object[]{"true"});
        }

        this.delete(uriBuilder.build(new Object[0]), progressMonitor);
    }

    public void removeIssue(String issueKey, ProgressMonitor progressMonitor) {
        this.removeIssue(issueKey, false, progressMonitor);
    }

    public void vote(final String issueKey, ProgressMonitor progressMonitor) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                WebResource votesResource = JerseyIssueRestClient.this.client.resource(baseUri + "/issue/" + issueKey + "/votes");
                votesResource.post();
                return null;
            }
        });
    }

    public void unvote(final String issueKey, ProgressMonitor progressMonitor) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                WebResource votesResource = JerseyIssueRestClient.this.client.resource(baseUri + "/issue/" + issueKey + "/votes");
                votesResource.delete();
                return null;
            }
        });
    }

    public void addWatcher(final String issueKey, @Nullable final String username, ProgressMonitor progressMonitor) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                Builder builder = JerseyIssueRestClient.this.client.resource(URI.create(baseUri + "/issue/" + issueKey + "/watchers"))
                        .type(MediaType.APPLICATION_JSON_TYPE);
                if (username != null) {
                    builder.post(JSONObject.quote(username));
                } else {
                    builder.post();
                }

                return null;
            }
        });
    }

    private String getLoggedUsername(ProgressMonitor progressMonitor) {
        Session session = this.sessionRestClient.getCurrentSession(progressMonitor);
        return session.getUsername();
    }

    public void removeWatcher(String issueKey, String username, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(URI.create(baseUri + "/issue/" + issueKey + "/watchers"));
        if (this.getVersionInfo(progressMonitor).getBuildNumber() >= 640) {
            uriBuilder.queryParam("username", new Object[]{username});
        } else {
            uriBuilder.path(username).build(new Object[0]);
        }

        this.delete(uriBuilder.build(new Object[0]), progressMonitor);
    }

    public void linkIssue(final LinkIssuesInput linkIssuesInput, final ProgressMonitor progressMonitor) {
        URI uri = UriBuilder.fromUri(this.baseUri).path("issueLink").build(new Object[0]);
        this.post(uri, new Callable<JSONObject>() {
            public JSONObject call() throws Exception {
                return (new LinkIssuesInputGenerator(JerseyIssueRestClient.this.getVersionInfo(progressMonitor))).generate(linkIssuesInput);
            }
        }, progressMonitor);
    }

    public void unlinkIssue(final URI linkUri, final ProgressMonitor progressMonitor) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                WebResource votesResource = JerseyIssueRestClient.this.client.resource(linkUri);
                votesResource.delete();
                return null;
            }
        });
    }

    public void addAttachment(ProgressMonitor progressMonitor, String issueKey, InputStream in, String filename) {
        this.addAttachments(progressMonitor, issueKey, new AttachmentInput(filename, in));
    }

    public void addAttachments(ProgressMonitor progressMonitor, final String issueKey, AttachmentInput... attachments) {
        final ArrayList<AttachmentInput> myAttachments = Lists.newArrayList(attachments);
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                MultiPart multiPartInput = new MultiPart();
                Iterator i$ = myAttachments.iterator();

                while (i$.hasNext()) {
                    AttachmentInput attachment = (AttachmentInput) i$.next();
                    BodyPart bp = new BodyPart(attachment.getInputStream(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
                    FormDataContentDispositionBuilder dispositionBuilder = FormDataContentDisposition.name("file");
                    dispositionBuilder.fileName(attachment.getFilename());
                    FormDataContentDisposition formDataContentDisposition = dispositionBuilder.build();
                    bp.setContentDisposition(formDataContentDisposition);
                    multiPartInput.bodyPart(bp);
                }

                JerseyIssueRestClient.this.postFileMultiPart(multiPartInput, issueKey);
                return null;
            }
        });
    }

    public InputStream getAttachment(ProgressMonitor pm, final URI attachmentUri) {
        return (InputStream) this.invoke(new Callable<InputStream>() {
            public InputStream call() throws Exception {
                WebResource attachmentResource = JerseyIssueRestClient.this.client.resource(attachmentUri);
                return (InputStream) attachmentResource.get(InputStream.class);
            }
        });
    }

    public void addAttachments(ProgressMonitor progressMonitor, final String issueKey, File... files) {
        final ArrayList<File> myFiles = Lists.newArrayList(files);
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                MultiPart multiPartInput = new MultiPart();
                Iterator i$ = myFiles.iterator();

                while (i$.hasNext()) {
                    File file = (File) i$.next();
                    FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", file);
                    multiPartInput.bodyPart(fileDataBodyPart);
                }

                JerseyIssueRestClient.this.postFileMultiPart(multiPartInput, issueKey);
                return null;
            }
        });
    }

    public void removeAttachment(ProgressMonitor progressMonitor, URI attachmentUri) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                WebResource votesResource = JerseyIssueRestClient.this.client.resource(attachmentUri);
                votesResource.delete();
                return null;
            }
        });
    }

    public void removeAttachment(ProgressMonitor progressMonitor, Integer attachmentId) {
        this.removeAttachment(progressMonitor, URI.create(baseUri + "/attachment/" + attachmentId));
    }

    public void addComment(final ProgressMonitor progressMonitor, String issueKey, final Comment comment) {
        this.post(URI.create(baseUri + "/issue/" + issueKey + "/comment"), new Callable<JSONObject>() {
            public JSONObject call() throws Exception {
                return (new CommentJsonGenerator(JerseyIssueRestClient.this.getVersionInfo(progressMonitor))).generate(comment);
            }
        }, progressMonitor);
    }

    public void removeComment(ProgressMonitor progressMonitor, String issueKey, Long commentId) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                WebResource votesResource = JerseyIssueRestClient.this.client.resource(baseUri + "/issue/" + issueKey + "/comment/" + commentId);
                votesResource.delete();
                return null;
            }
        });
    }

    public void updateComment(ProgressMonitor progressMonitor, String issueKey, Comment comment) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                JSONObject jsonObject = new JSONObject();
                WebResource issueResource = JerseyIssueRestClient.this.client.resource(baseUri + "/issue/" + issueKey + "/comment/" + comment.getId());
                jsonObject.put("body", comment.getBody());
                issueResource.put(jsonObject);
                return null;
            }
        });
    }

    private void postFileMultiPart(MultiPart multiPartInput, String issueKey) {
        WebResource attachmentsResource = this.client.resource(URI.create(baseUri + "/issue/" + issueKey + "/attachments"));
        Builder builder = attachmentsResource.type(MultiPartMediaTypes.createFormData());
        builder.header("X-Atlassian-Token", "nocheck");
        builder.post(multiPartInput);
    }

    public void watch(String issueKey, ProgressMonitor progressMonitor) {
        this.addWatcher(issueKey, (String) null, progressMonitor);
    }

    public void unwatch(String issueKey, ProgressMonitor progressMonitor) {
        this.removeWatcher(issueKey, this.getLoggedUsername(progressMonitor), progressMonitor);
    }

    public BasicIssue createIssue(IssueInput issue, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(this.baseUri);
        uriBuilder.path("issue");
        return (BasicIssue) this.postAndParse(uriBuilder.build(new Object[0]), InputGeneratorCallable.create(new IssueInputJsonGenerator(), issue), this.basicIssueParser, progressMonitor);
    }

    public BulkIssues createIssues(BulkIssueInput issues, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(this.baseUri);
        uriBuilder.path("issue/bulk");
        return (BulkIssues) this.postAndParse(uriBuilder.build(new Object[0]), InputGeneratorCallable.create(new BulkIssueInputJsonGenerator(), issues), this.bulkIssueParser, progressMonitor);
    }

    public Iterable<CimProject> getCreateIssueMetadata(@Nullable GetCreateIssueMetadataOptions options, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(this.baseUri).path("issue/createmeta");
        if (options != null) {
            if (options.projectIds != null) {
                uriBuilder.queryParam("projectIds", new Object[]{Joiner.on(",").join(options.projectIds)});
            }

            if (options.projectKeys != null) {
                uriBuilder.queryParam("projectKeys", new Object[]{Joiner.on(",").join(options.projectKeys)});
            }

            if (options.issueTypeIds != null) {
                uriBuilder.queryParam("issuetypeIds", new Object[]{Joiner.on(",").join(options.issueTypeIds)});
            }

            Iterable<String> issueTypeNames = options.issueTypeNames;
            if (issueTypeNames != null) {
                Iterator i$ = issueTypeNames.iterator();

                while (i$.hasNext()) {
                    String name = (String) i$.next();
                    uriBuilder.queryParam("issuetypeNames", new Object[]{name});
                }
            }

            Iterable<String> expandos = options.expandos;
            if (expandos != null && expandos.iterator().hasNext()) {
                uriBuilder.queryParam("expand", new Object[]{Joiner.on(",").join(expandos)});
            }
        }

        return (Iterable) this.getAndParse(uriBuilder.build(new Object[0]), this.createIssueMetadataJsonParser, progressMonitor);
    }

    public CimEditIssuemetaData getEditIssueMetadata(@Nullable String issueKey, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(this.baseUri).path("issue/" + issueKey + "/editmeta");
        return this.getAndParse(uriBuilder.build(new Object[0]), this.editIssueMetadataJsonParser, progressMonitor);
    }


    public void addWorklog(URI worklogUri, final WorklogInput worklogInput, ProgressMonitor progressMonitor) {
        UriBuilder uriBuilder = UriBuilder.fromUri(worklogUri).queryParam("adjustEstimate", new Object[]{worklogInput.getAdjustEstimate().restValue});
        switch (worklogInput.getAdjustEstimate()) {
            case NEW:
                uriBuilder.queryParam("newEstimate", new Object[]{Strings.nullToEmpty(worklogInput.getAdjustEstimateValue())});
                break;
            case MANUAL:
                uriBuilder.queryParam("reduceBy", new Object[]{Strings.nullToEmpty(worklogInput.getAdjustEstimateValue())});
        }

        this.post(uriBuilder.build(new Object[0]), new Callable<JSONObject>() {
            public JSONObject call() throws Exception {
                return (new WorklogInputJsonGenerator()).generate(worklogInput);
            }
        }, progressMonitor);
    }

    static {
        DEFAULT_EXPANDS = EnumSet.of(Expandos.NAMES, Expandos.SCHEMA, Expandos.TRANSITIONS);
        EXPANDO_TO_PARAM = new Function<Expandos, String>() {
            public String apply(Expandos from) {
                return from.getFieldName();
            }
        };
    }
}
