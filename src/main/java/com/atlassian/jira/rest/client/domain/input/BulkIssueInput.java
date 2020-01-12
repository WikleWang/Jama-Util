package com.atlassian.jira.rest.client.domain.input;

import com.google.common.base.Objects;

import java.util.List;

public class BulkIssueInput extends IssueInput {

    private final List<IssueInput> issueUpdates;

    public BulkIssueInput(List<IssueInput> issueUpdates) {
        super(issueUpdates.get(0).getFields());
        this.issueUpdates = issueUpdates;
    }

    public List<IssueInput> getIssueUpdates() {
        return issueUpdates;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("issueUpdates", this.issueUpdates).toString();
    }
}
