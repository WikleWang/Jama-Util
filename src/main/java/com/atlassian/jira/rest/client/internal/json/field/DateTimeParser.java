package com.atlassian.jira.rest.client.internal.json.field;

import org.joda.time.DateTime;

import java.util.Date;

public class DateTimeParser implements FieldParser<DateTime> {
    @Override
    public String getValue(DateTime dateTime) {
        return dateTime == null ? FieldParser.EMPTY : FieldParser.DATETIME_FORMAT.format(dateTime.toDate());
    }
}
