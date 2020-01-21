package com.atlassian.jira.rest.client.internal.json.field;

import com.atlassian.jira.rest.client.domain.Project;

public class ProjectParser implements FieldParser<Project> {
    @Override
    public String getValue(Project project) {
        return project == null ? FieldParser.EMPTY : project.getName();
    }
}
