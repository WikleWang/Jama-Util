package com.atlassian.jira.rest.client.internal.json.field;

import com.atlassian.jira.rest.client.domain.IssueType;

public class IssueTypeParser implements FieldParser<IssueType> {

    @Override
    public String getValue(IssueType issueType) {
        return issueType == null ? FieldParser.EMPTY : issueType.getName();
    }


}
