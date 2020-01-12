package com.atlassian.jira.rest.client.internal.jersey;

import com.atlassian.jira.rest.client.GroupRestClient;
import com.atlassian.jira.rest.client.ProgressMonitor;
import com.atlassian.jira.rest.client.domain.GroupUserPicker;
import com.atlassian.jira.rest.client.domain.User;
import com.atlassian.jira.rest.client.internal.json.GroupUserPickerJsonParser;
import com.atlassian.jira.rest.client.internal.json.GroupsJsonParser;
import com.atlassian.jira.rest.client.internal.json.MemberJsonParser;
import com.atlassian.jira.rest.client.internal.json.gen.GroupInputGenerator;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class JerseyGroupRestClient extends AbstractJerseyRestClient implements GroupRestClient {

    private static final String USER_URI_PREFIX = "user";
    private static final String MAX_RESULTS_ATTRIBUTE = "maxResults";
    private static final String START_AT_ATTRIBUTE = "startAt";
    private static final Integer DEFAULT_START_AT = 0;
    private static final Integer DEFAULT_MAX_RESULTS = 200;
    private static final String USERNAME_ATTRIBUTE = "username";
    private static final String QUERY_ATTRIBUTE = "query";
    private final GroupsJsonParser groupsJsonParser = new GroupsJsonParser();
    private final MemberJsonParser memberJsonParser = new MemberJsonParser();
    private final GroupUserPickerJsonParser groupUserPickerJsonParser = new GroupUserPickerJsonParser();

    public JerseyGroupRestClient(URI baseUri, ApacheHttpClient client) {
        super(baseUri, client);
    }

    @Override
    public List<String> searchGroup(String query, Integer maxResults, Integer startAt, ProgressMonitor progressMonitor) {
        UriBuilder uri = UriBuilder.fromUri(this.baseUri).path("groups/picker").queryParam(QUERY_ATTRIBUTE, new Object[]{query});
        URI groupUri = setLimit(uri, maxResults, startAt);
        return (List<String>) this.getAndParse(groupUri, this.groupsJsonParser, progressMonitor);
    }

    @Override
    public List<User> getMembers(String groupName, Integer maxResults, Integer startAt, ProgressMonitor progressMonitor) {
        UriBuilder uri = UriBuilder.fromUri(this.baseUri).path("group/member").queryParam("groupname", new Object[]{groupName});
        URI memberUri = setLimit(uri, maxResults, startAt);
        return (List<User>) this.getAndParse(memberUri, this.memberJsonParser, progressMonitor);
    }

    @Override
    public void addGroup(String groupName, ProgressMonitor progressMonitor) {
        URI uri = UriBuilder.fromUri(this.baseUri).path("group").build(new Object[0]);
        this.post(uri, new Callable<JSONObject>() {
            public JSONObject call() throws Exception {
                return (new GroupInputGenerator().generate(groupName));
            }
        }, progressMonitor);
    }

    @Override
    public void removeGroup(String groupName, ProgressMonitor progressMonitor) {
        URI uri = UriBuilder.fromUri(this.baseUri).path("group").queryParam("groupname", groupName).build(new Object[0]);
        this.delete(uri, progressMonitor);
    }

    @Override
    public void addUser(String groupName, String name, ProgressMonitor progressMonitor) {
        URI uri = UriBuilder.fromUri(this.baseUri).path("group").path(USER_URI_PREFIX).queryParam("groupname", groupName).build(new Object[0]);
        this.post(uri, new Callable<JSONObject>() {
            public JSONObject call() throws Exception {
                return (new GroupInputGenerator()).generate(name);
            }
        }, progressMonitor);
    }

    @Override
    public void removeUser(String groupName, String username, ProgressMonitor progressMonitor) {
        URI uri = UriBuilder.fromUri(this.baseUri).path("group").path(USER_URI_PREFIX)
                .queryParam("groupname", groupName)
                .queryParam(USERNAME_ATTRIBUTE, username).build(new Object[0]);
        this.delete(uri, progressMonitor);
    }

    @Override
    public GroupUserPicker getGroupUserPicker(String query, Integer maxResults, Integer startAt, Iterable<String> projectId, Iterable<String> issueTypeId, ProgressMonitor progressMonitor) {
        UriBuilder uri = UriBuilder.fromUri(this.baseUri).path("groupuserpicker")
                .queryParam("query", new Object[]{query});
        if (projectId != null) {
            uri.queryParam("projectId", getIterableValues(projectId));
        }

        if (issueTypeId != null) {
            uri.queryParam("issueTypeId", getIterableValues(issueTypeId));
        }

        URI memberUri = setLimit(uri, maxResults, startAt);
        return (GroupUserPicker) this.getAndParse(memberUri, this.groupUserPickerJsonParser, progressMonitor);
    }

    private URI setLimit(UriBuilder builder, Integer maxResults, Integer startAt) {
        if (maxResults == null) maxResults = DEFAULT_MAX_RESULTS;
        if (startAt == null) startAt = DEFAULT_START_AT;
        return builder.queryParam(MAX_RESULTS_ATTRIBUTE, new Object[]{maxResults}).queryParam(START_AT_ATTRIBUTE, new Object[]{startAt}).build(new Object[0]);
    }

    protected void delete(final URI uri, ProgressMonitor progressMonitor) {
        this.invoke(new Callable<Void>() {
            public Void call() throws Exception {
                WebResource webResource = JerseyGroupRestClient.this.client.resource(uri);
                webResource.delete();
                return null;
            }
        });
    }

    private String getIterableValues(Iterable<String> iterable) {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            buffer.append(buffer.length() > 0 ? "," + next : next);
        }
        return buffer.toString();
    }
}