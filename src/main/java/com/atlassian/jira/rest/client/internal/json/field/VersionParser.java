package com.atlassian.jira.rest.client.internal.json.field;

import com.atlassian.jira.rest.client.domain.Version;

public class VersionParser implements FieldParser<Version> {
    @Override
    public String getValue(Version version) {
        return version == null ? FieldParser.EMPTY : version.getName();
    }
}
