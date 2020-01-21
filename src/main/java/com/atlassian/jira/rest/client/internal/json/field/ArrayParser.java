package com.atlassian.jira.rest.client.internal.json.field;

import com.atlassian.jira.rest.client.domain.*;
import org.codehaus.jettison.json.JSONArray;

public class ArrayParser implements FieldParser<JSONArray> {

    @Override
    public String getValue(JSONArray array) throws Exception {
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Object object = array.get(i);
                if(object instanceof IssueType) {

                } else if(object instanceof Version) {

                } else if(object instanceof String) {

                } else if(object instanceof Component) {

                } else if(object instanceof Worklog) {

                } else if(object instanceof Attachment) {

                } else if(object instanceof IssueLink) {

                }
            }
        }
        return FieldParser.EMPTY;
    }


}
