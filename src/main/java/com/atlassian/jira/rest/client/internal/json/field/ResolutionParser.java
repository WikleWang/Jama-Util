package com.atlassian.jira.rest.client.internal.json.field;

import com.atlassian.jira.rest.client.domain.Resolution;

public class ResolutionParser implements FieldParser<Resolution> {
    @Override
    public String getValue(Resolution resolution) {
        return resolution == null ? FieldParser.EMPTY : resolution.getName();
    }
}
