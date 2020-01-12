//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.domain.GroupUserPicker;
import com.atlassian.jira.rest.client.domain.User;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupUserPickerJsonParser implements JsonObjectParser<GroupUserPicker> {


    public GroupUserPickerJsonParser() {
    }

    public GroupUserPicker parse(JSONObject json) throws JSONException {
        return new GroupUserPicker(json.getJSONObject("users"), json.getJSONObject("groups"));
    }
}
