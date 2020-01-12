//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.ExpandableProperty;
import com.atlassian.jira.rest.client.domain.BasicUser;
import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.domain.Schema;
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

public class FieldJsonParser implements JsonArrayParser<List<Field>> {
    public FieldJsonParser() {
    }

    public List<Field> parse(JSONArray array) throws JSONException {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            List<String> clauseNames = new ArrayList<>();
            JSONArray clauseNamesArray = object.getJSONArray("clauseNames");
            for (int j = 0; j < clauseNamesArray.length(); j++) {
                clauseNames.add(clauseNamesArray.getString(j));
            }

            Schema schema = null;

            if (object.has("schema")) {
                JSONObject schemaObj = object.getJSONObject("schema");
                String system = "";
                if (schemaObj.has("system")) {
                    system = schemaObj.getString("system");
                }
                String items = "";
                if (schemaObj.has("items")) {
                    items = schemaObj.getString("items");
                }
                String custom = "";
                if (schemaObj.has("custom")) {
                    custom = schemaObj.getString("custom");
                }
                Integer customId = null;
                if (schemaObj.has("customId")) {
                    customId = schemaObj.getInt("customId");
                }
                schema = new Schema(schemaObj.getString("type"), system, items, custom, customId);
            }


            fields.add(new Field(object.getString("id"), object.getString("name"),
                    object.getBoolean("custom"), object.getBoolean("orderable"),
                    object.getBoolean("navigable"), object.getBoolean("searchable"), clauseNames, schema));
        }
        return fields;
    }
}
