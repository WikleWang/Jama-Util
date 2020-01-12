//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.domain.BasicUser;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupsJsonParser implements JsonObjectParser<List<String>> {
    public GroupsJsonParser() {
    }

    public List<String> parse(JSONObject json) throws JSONException {
        List<String> result = new ArrayList<>();
        JSONArray groups = json.getJSONArray("groups");
        for (int i = 0; i < groups.length(); i++) {
            result.add(groups.getJSONObject(i).getString("name"));
        }
        return result;
    }
}
