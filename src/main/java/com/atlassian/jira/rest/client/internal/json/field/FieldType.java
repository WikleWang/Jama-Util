package com.atlassian.jira.rest.client.internal.json.field;

public enum FieldType {

    ISSUE_TYPE("issuetype", null),
    NUMBER("number", null),
    PROJECT("project", null),
    ARRAY_VERSION("array", "version"),
    RESOLUTION("resolution", null),
    STRING("string", null),
    DATETIME("datetime", null),
    WATCHES("watches",null),
    PRIORITY("priority", null),
    ANY("any", null),
    ARRAY_STRING("array", "string"),
    OPTION("option",null),
    ARRAY_ISSUE_LINKS("array","issuelinks"),
    USER("user",null),
    STATUS("status", null),
    ARRAY_COMPONENT("array","component"),
    TIME_TRACKING("timetracking", null),
    SECURITY_LEVEL("securitylevel", null),
    ARRAY_ATTACHMENT("array","attachment"),
    PROGRESS("progress", null),
    DATE("date", null),
    COMMENTS_PAGE("comments-page", null),
    VOTES("votes", null),
    ARRAY_WORKLOG("array","worklog");
    FieldType(String type,String items) {
        this.type = type;
        this.items = items;
    }

    private final String type;
    private final String items;


    public String getType() {
        return type;
    }

    public String getItems() {
        return items;
    }
}
