package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.domain.User;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UsersJsonParser implements JsonArrayParser<List<User>> {
    private final UserJsonParser userJsonParser = new UserJsonParser();

    @Override
    public List<User> parse(JSONArray json) throws JSONException {
        List<User> users = new ArrayList<>();
        if (json != null) {
            for (int i = 0; i < json.length(); i++) {
                User user = userJsonParser.parse(json.getJSONObject(i));
                users.add(user);
            }
        }
        return users;
    }
}
