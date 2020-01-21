package com.atlassian.jira.rest.client.internal.json.field;

import com.atlassian.jira.rest.client.domain.BasicUser;
import com.atlassian.jira.rest.client.domain.Watchers;

import java.util.Iterator;

public class WatchersParser implements FieldParser<Watchers> {
    @Override
    public String getValue(Watchers watchers) {
        if (watchers != null) {
            Iterable<BasicUser> users = watchers.getUsers();
            StringBuffer value = new StringBuffer();
            Iterator<BasicUser> iterator = users.iterator();
            while (iterator.hasNext()) {
                String user = iterator.next().getDisplayName();
                value.append(value.length() > 0 ? ", " + user : user);
            }
            return value.toString();
        }
        return FieldParser.EMPTY;
    }
}
