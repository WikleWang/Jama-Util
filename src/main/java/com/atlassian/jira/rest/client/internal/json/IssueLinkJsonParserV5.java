package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.domain.IssueLinkType.Direction;

import java.net.URI;
import java.util.Set;

import com.google.common.collect.Sets;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class IssueLinkJsonParserV5 implements JsonObjectParser<IssueLink> {
    private final IssuelinksTypeJsonParserV5 issuelinksTypeJsonParserV5 = new IssuelinksTypeJsonParserV5();
    private final JsonFieldParser fieldParser = new JsonFieldParser();
    private static Set<String> SPECIAL_FIELDS = Sets.newHashSet(IssueFieldId.ids());

    public IssueLinkJsonParserV5() {
    }

    public IssueLink parse(JSONObject json) throws JSONException {
        IssuelinksType issuelinksType = this.issuelinksTypeJsonParserV5.parse(json.getJSONObject("type"));
        Direction direction;
        JSONObject link;
        if (json.has("inwardIssue")) {
            link = json.getJSONObject("inwardIssue");
            direction = Direction.INBOUND;
        } else {
            link = json.getJSONObject("outwardIssue");
            direction = Direction.OUTBOUND;
        }
        JSONObject fields = link.getJSONObject("fields");

        String key = link.getString("key");
        fields.put("key", key);
        Long id = JsonParseUtil.getOptionalLong(link, "id");
        URI targetIssueUri = JsonParseUtil.parseURI(link.getString("self"));
        IssueLinkType issueLinkType = new IssueLinkType(issuelinksType.getName(), direction.equals(Direction.INBOUND) ? issuelinksType.getInward() : issuelinksType.getOutward(), direction);
        return new IssueLink(URI.create(json.getString("self")), key, fields, id, targetIssueUri, issueLinkType);
    }


}
