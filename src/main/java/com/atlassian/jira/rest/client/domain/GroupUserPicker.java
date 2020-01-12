package com.atlassian.jira.rest.client.domain;

import com.google.common.base.Objects;
import org.codehaus.jettison.json.JSONObject;

public class GroupUserPicker {
    private final JSONObject users;
    private final JSONObject groups;

    public GroupUserPicker(JSONObject users, JSONObject groups) {
        this.users = users;
        this.groups = groups;
    }

    public JSONObject getUsers() {
        return users;
    }

    public JSONObject getGroups() {
        return groups;
    }


    public String toString() {
        return Objects.toStringHelper(this).add("users", this.users).add("groups",this.groups).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupUserPicker that = (GroupUserPicker) o;
        return java.util.Objects.equals(users, that.users) &&
                java.util.Objects.equals(groups, that.groups);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.users, this.groups});
    }
}
