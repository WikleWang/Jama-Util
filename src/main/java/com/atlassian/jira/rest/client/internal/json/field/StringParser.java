package com.atlassian.jira.rest.client.internal.json.field;

public class StringParser implements FieldParser<String> {

    @Override
    public String getValue(String value) {
        return value;
    }


}
