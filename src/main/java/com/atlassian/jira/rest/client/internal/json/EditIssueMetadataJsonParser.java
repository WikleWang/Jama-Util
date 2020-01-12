package com.atlassian.jira.rest.client.internal.json;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class EditIssueMetadataJsonParser implements JsonObjectParser<CimEditIssuemetaData> {
    final CimFieldsInfoMapJsonParser fieldsParser = new CimFieldsInfoMapJsonParser();

    @Override
    public CimEditIssuemetaData parse(JSONObject jsonObject) throws JSONException {
        return new CimEditIssuemetaData(this.fieldsParser.parse(jsonObject.getJSONObject("fields")));
    }

}
