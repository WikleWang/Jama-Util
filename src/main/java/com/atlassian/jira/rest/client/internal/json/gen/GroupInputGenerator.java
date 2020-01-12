package com.atlassian.jira.rest.client.internal.json.gen;

import com.atlassian.jira.rest.client.domain.ServerInfo;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class GroupInputGenerator implements JsonGenerator<String> {

    @Override
    public JSONObject generate(String groupName) throws JSONException {
        JSONObject group = new JSONObject();
        group.put("name", groupName);
        return group;
    }
}
