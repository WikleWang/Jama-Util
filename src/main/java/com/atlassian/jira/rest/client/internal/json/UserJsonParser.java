//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.ExpandableProperty;
import com.atlassian.jira.rest.client.domain.BasicUser;
import com.atlassian.jira.rest.client.domain.User;
import com.google.common.collect.Maps;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class UserJsonParser implements JsonObjectParser<User> {
    public UserJsonParser() {
    }

    public User parse(JSONObject json) throws JSONException {
        BasicUser basicUser = JsonParseUtil.parseBasicUser(json);
        String timezone = JsonParseUtil.getOptionalString(json, "timeZone");
        String avatarUrl = JsonParseUtil.getOptionalString(json, "avatarUrl");
        Map<String, URI> avatarUris = Maps.newHashMap();
        if (avatarUrl != null) {
            URI avatarUri = JsonParseUtil.parseURI(avatarUrl);
            avatarUris.put(User.S48_48, avatarUri);
        } else {
            JSONObject avatarUrlsJson = json.getJSONObject("avatarUrls");
            Iterator iterator = avatarUrlsJson.keys();

            while(iterator.hasNext()) {
                String key = (String)iterator.next();
                avatarUris.put(key, JsonParseUtil.parseURI(avatarUrlsJson.getString(key)));
            }
        }

        String emailAddress = JsonParseUtil.getOptionalString(json, "emailAddress");
        ExpandableProperty<String> groups = null;
        if(json.has("groups")) {
            groups = JsonParseUtil.parseExpandableProperty(json.getJSONObject("groups"), new JsonObjectParser<String>() {
                public String parse(JSONObject json) throws JSONException {
                    return json.getString("name");
                }
            });
        }
        return new User(basicUser.getSelf(), basicUser.getName(), basicUser.getDisplayName(), emailAddress, groups, avatarUris, timezone);
    }
}
