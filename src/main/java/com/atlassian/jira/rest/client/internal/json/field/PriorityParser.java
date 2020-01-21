package com.atlassian.jira.rest.client.internal.json.field;

import com.atlassian.jira.rest.client.domain.BasicPriority;

public class PriorityParser implements FieldParser<BasicPriority> {
    @Override
    public String getValue(BasicPriority priority) {
        return priority == null ? FieldParser.EMPTY : priority.getName();
    }
}
