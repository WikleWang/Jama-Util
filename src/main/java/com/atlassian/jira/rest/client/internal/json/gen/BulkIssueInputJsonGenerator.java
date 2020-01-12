
package com.atlassian.jira.rest.client.internal.json.gen;

import com.atlassian.jira.rest.client.domain.input.BulkIssueInput;
import com.atlassian.jira.rest.client.domain.input.IssueInput;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.Iterator;

public class BulkIssueInputJsonGenerator implements JsonGenerator<BulkIssueInput> {
    private final ComplexIssueInputFieldValueJsonGenerator complexIssueInputFieldValueJsonGenerator = new ComplexIssueInputFieldValueJsonGenerator();

    public BulkIssueInputJsonGenerator() {
    }

    public JSONObject generate(BulkIssueInput issues) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray issueUpdates = new JSONArray();
        if(issues!= null) {
            Iterator<IssueInput> i$ = issues.getIssueUpdates().iterator();
            while(i$.hasNext()) {
                IssueInput issueInput = (IssueInput)i$.next();
                IssueInputJsonGenerator generator = new IssueInputJsonGenerator();
                JSONObject issue = generator.generate(issueInput);
                issueUpdates.put(issue);
            }
        }
        jsonObject.put("issueUpdates", issueUpdates);
        return jsonObject;
    }
}
