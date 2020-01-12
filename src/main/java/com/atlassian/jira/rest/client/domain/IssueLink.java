package com.atlassian.jira.rest.client.domain;

import com.google.common.base.Objects;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;
import javax.annotation.Nullable;

public class IssueLink {
    private final URI self;
    private final String targetIssueKey;
    private final JSONObject targetIssueInfo;
    @Nullable
    private final Long targetIssueId;
    private final URI targetIssueUri;
    private final IssueLinkType issueLinkType;

    public IssueLink(URI self, String targetIssueKey, JSONObject targetIssueInfo, @Nullable Long targetIssueId, URI targetIssueUri, IssueLinkType issueLinkType) {
        this.self = self;
        this.targetIssueKey = targetIssueKey;
        this.targetIssueInfo = targetIssueInfo;
        this.targetIssueId = targetIssueId;
        this.targetIssueUri = targetIssueUri;
        this.issueLinkType = issueLinkType;

    }

    public String getTargetIssueKey() {
        return this.targetIssueKey;
    }

    @Nullable
    public Long getTargetIssueId() {
        return this.targetIssueId;
    }

    public URI getTargetIssueUri() {
        return this.targetIssueUri;
    }

    public IssueLinkType getIssueLinkType() {
        return this.issueLinkType;
    }


    public JSONObject getTargetIssueInfo() {
        return targetIssueInfo;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("self", this.self).add("targetIssueInfo",this.targetIssueInfo)
                .add("targetIssueKey", this.targetIssueKey).add("targetIssueId", this.targetIssueId).add("targetIssueUri", this.targetIssueUri).add("issueLinkType", this.issueLinkType).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IssueLink)) {
            return false;
        } else {
            IssueLink that = (IssueLink) obj;
            return Objects.equal(this.targetIssueKey, that.targetIssueKey) && Objects.equal(this.targetIssueId, that.targetIssueId) && Objects.equal(this.targetIssueUri, that.targetIssueUri) && Objects.equal(this.issueLinkType, that.issueLinkType);
        }
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.targetIssueKey, this.targetIssueId, this.targetIssueUri, this.issueLinkType});
    }
}
