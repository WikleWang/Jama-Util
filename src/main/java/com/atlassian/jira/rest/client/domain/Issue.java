package com.atlassian.jira.rest.client.domain;

import com.atlassian.jira.rest.client.ExpandableResource;
import com.google.common.base.Objects;
import java.net.URI;
import java.util.*;
import javax.annotation.Nullable;
import javax.ws.rs.core.UriBuilder;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;

public class Issue extends BasicIssue implements ExpandableResource {
    private final BasicStatus status;
    private BasicIssueType issueType;
    private BasicProject project;
    private final URI transitionsUri;
    private final Iterable<String> expandos;
    private final Collection<BasicComponent> components;
    private final String summary;
    @Nullable
    private final String description;
    @Nullable
    private BasicUser reporter;
    private BasicUser assignee;
    @Nullable
    private final BasicResolution resolution;
    private Collection<Field> fields;
    private DateTime creationDate;
    private DateTime updateDate;
    private DateTime dueDate;
    private final BasicPriority priority;
    private final BasicVotes votes;
    @Nullable
    private final Collection<Version> fixVersions;
    @Nullable
    private final Collection<Version> affectedVersions;
    private final Collection<Comment> comments;
    @Nullable
    private final Collection<IssueLink> issueLinks;
    private final Collection<Attachment> attachments;
    private final Collection<Worklog> worklogs;
    private final BasicWatchers watchers;
    @Nullable
    private final TimeTracking timeTracking;
    @Nullable
    private final Collection<Subtask> subtasks;
    @Nullable
    private final Collection<ChangelogGroup> changelog;
    private final Set<String> labels;
    @Nullable
    private final JSONObject rawObject;

    public Issue(String summary, URI self, String key, @Nullable Long id, BasicProject project, BasicIssueType issueType, BasicStatus status, String description, @Nullable BasicPriority priority, @Nullable BasicResolution resolution, Collection<Attachment> attachments, @Nullable BasicUser reporter, @Nullable BasicUser assignee, DateTime creationDate, DateTime updateDate, DateTime dueDate, Collection<Version> affectedVersions, Collection<Version> fixVersions, Collection<BasicComponent> components, @Nullable TimeTracking timeTracking, Collection<Field> fields, Collection<Comment> comments, @Nullable URI transitionsUri, @Nullable Collection<IssueLink> issueLinks, BasicVotes votes, Collection<Worklog> worklogs, BasicWatchers watchers, Iterable<String> expandos, @Nullable Collection<Subtask> subtasks, @Nullable Collection<ChangelogGroup> changelog, Set<String> labels, @Nullable JSONObject rawObject) {
        super(self, key, id);
        this.summary = summary;
        this.project = project;
        this.status = status;
        this.description = description;
        this.resolution = resolution;
        this.expandos = expandos;
        this.comments = comments;
        this.attachments = attachments;
        this.fields = fields;
        this.issueType = issueType;
        this.reporter = reporter;
        this.assignee = assignee;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.dueDate = dueDate;
        this.transitionsUri = transitionsUri;
        this.issueLinks = issueLinks;
        this.votes = votes;
        this.worklogs = worklogs;
        this.watchers = watchers;
        this.fixVersions = fixVersions;
        this.affectedVersions = affectedVersions;
        this.components = components;
        this.priority = priority;
        this.timeTracking = timeTracking;
        this.subtasks = subtasks;
        this.changelog = changelog;
        this.labels = labels;
        this.rawObject = rawObject;
    }

    public BasicStatus getStatus() {
        return this.status;
    }

    @Nullable
    public BasicUser getReporter() {
        return this.reporter;
    }

    @Nullable
    public BasicUser getAssignee() {
        return this.assignee;
    }

    public String getSummary() {
        return this.summary;
    }

    @Nullable
    public BasicPriority getPriority() {
        return this.priority;
    }

    @Nullable
    public Iterable<IssueLink> getIssueLinks() {
        return this.issueLinks;
    }

    @Nullable
    public Iterable<Subtask> getSubtasks() {
        return this.subtasks;
    }

    public Iterable<Field> getFields() {
        return this.fields;
    }

    @Nullable
    public Field getField(String id) {
        Iterator i$ = this.fields.iterator();

        Field field;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            field = (Field)i$.next();
        } while(!field.getId().equals(id));

        return field;
    }

    @Nullable
    public Field getFieldByName(String name) {
        Iterator i$ = this.fields.iterator();

        Field field;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            field = (Field)i$.next();
        } while(!field.getName().equals(name));

        return field;
    }

    public Iterable<String> getExpandos() {
        return this.expandos;
    }

    public BasicIssueType getIssueType() {
        return this.issueType;
    }

    public Iterable<Attachment> getAttachments() {
        return this.attachments;
    }

    public URI getAttachmentsUri() {
        return UriBuilder.fromUri(this.getSelf()).path("attachments").build(new Object[0]);
    }

    public URI getWorklogUri() {
        return UriBuilder.fromUri(this.getSelf()).path("worklog").build(new Object[0]);
    }

    public Iterable<Comment> getComments() {
        return this.comments;
    }

    public URI getCommentsUri() {
        return UriBuilder.fromUri(this.getSelf()).path("comment").build(new Object[0]);
    }

    public BasicProject getProject() {
        return this.project;
    }

    @Nullable
    public BasicVotes getVotes() {
        return this.votes;
    }

    public Iterable<Worklog> getWorklogs() {
        return this.worklogs;
    }

    @Nullable
    public BasicWatchers getWatchers() {
        return this.watchers;
    }

    @Nullable
    public Iterable<Version> getFixVersions() {
        return this.fixVersions;
    }

    @Nullable
    public URI getTransitionsUri() {
        return this.transitionsUri;
    }

    @Nullable
    public Iterable<Version> getAffectedVersions() {
        return this.affectedVersions;
    }

    public Iterable<BasicComponent> getComponents() {
        return this.components;
    }

    public Set<String> getLabels() {
        return this.labels;
    }

    @Nullable
    public Iterable<ChangelogGroup> getChangelog() {
        return this.changelog;
    }

    public URI getVotesUri() {
        return UriBuilder.fromUri(this.getSelf()).path("votes").build(new Object[0]);
    }

    @Nullable
    public BasicResolution getResolution() {
        return this.resolution;
    }

    public DateTime getCreationDate() {
        return this.creationDate;
    }

    public DateTime getUpdateDate() {
        return this.updateDate;
    }

    public DateTime getDueDate() {
        return this.dueDate;
    }

    @Nullable
    public TimeTracking getTimeTracking() {
        return this.timeTracking;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public JSONObject getRawObject() {
        return this.rawObject;
    }

    public String toString() {
        return Objects.toStringHelper(this).addValue(super.toString()).add("project", this.project).add("status", this.status).add("description", this.description).add("expandos", this.expandos).add("resolution", this.resolution).add("reporter", this.reporter).add("assignee", this.assignee).addValue("\n").add("fields", this.fields).addValue("\n").add("affectedVersions", this.affectedVersions).addValue("\n").add("fixVersions", this.fixVersions).addValue("\n").add("components", this.components).addValue("\n").add("issueType", this.issueType).add("creationDate", this.creationDate).add("updateDate", this.updateDate).addValue("\n").add("dueDate", this.dueDate).addValue("\n").add("attachments", this.attachments).addValue("\n").add("comments", this.comments).addValue("\n").add("transitionsUri", this.transitionsUri).add("issueLinks", this.issueLinks).addValue("\n").add("votes", this.votes).addValue("\n").add("worklogs", this.worklogs).addValue("\n").add("watchers", this.watchers).add("timeTracking", this.timeTracking).add("changelog", this.changelog).add("labels", this.labels).toString();
    }

    public Map<String,String> toKeyValue() {
        Map<String,String> data = new HashMap<>();


        return data;
    }
}
