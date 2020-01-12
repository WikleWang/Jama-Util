package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.BulkIssues;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BulkIssueJsonParser implements JsonObjectParser<BulkIssues> {
    public BulkIssueJsonParser() {
    }

    public BulkIssues parse(JSONObject json) throws JSONException {
        JSONArray issues = json.getJSONArray("issues");
        List<BasicIssue> basicIssues = new ArrayList<>();
        if (issues != null) {
            BasicIssueJsonParser parser = new BasicIssueJsonParser();
            for (int i = 0; i < issues.length(); i++) {
                BasicIssue issue = parser.parse(issues.getJSONObject(i));
                basicIssues.add(issue);
            }
        }

        List<String> errorList = new ArrayList<>();
        JSONArray errors = json.getJSONArray("errors");
        if (errors != null) {
            for (int i = 0; i < errors.length(); i++) {
                errorList.add(errors.getString(i));
            }
        }
        return new BulkIssues(basicIssues, errorList);
    }

}
