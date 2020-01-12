//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.ExpandableProperty;
import com.atlassian.jira.rest.client.domain.BasicUser;
import com.atlassian.jira.rest.client.domain.User;
import com.google.common.collect.Maps;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MemberJsonParser implements JsonObjectParser<List<User>> {
    private final UserJsonParser userJsonParser = new UserJsonParser();

    public MemberJsonParser() {
    }

    public List<User> parse(JSONObject json) throws JSONException {
        List<User> users = new ArrayList<>();
        JSONArray values = json.getJSONArray("values");
        for(int i=0;i<values.length();i++) {
            JSONObject user = values.getJSONObject(i);
            users.add(userJsonParser.parse(user));
        }
        return users;
    }
}
