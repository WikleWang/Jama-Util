package com.atlassian.jira.rest.client.internal.json.field;

public class NumberParser implements FieldParser<Integer> {
    @Override
    public String getValue(Integer integer) {
        return integer == null ? FieldParser.EMPTY: integer.toString();
    }


}
