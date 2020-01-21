package com.atlassian.jira.rest.client.internal.json.field;

import java.text.SimpleDateFormat;

public interface FieldParser<T> {

    String getValue(T t) throws Exception;

    public static final String EMPTY = "";

    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

}
