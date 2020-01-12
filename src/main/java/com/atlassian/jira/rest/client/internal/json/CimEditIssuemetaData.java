package com.atlassian.jira.rest.client.internal.json;

import com.atlassian.jira.rest.client.domain.CimFieldInfo;
import com.google.common.base.Objects;

import java.util.Map;

public class CimEditIssuemetaData {
    private final Map<String, CimFieldInfo> fields;


    public CimEditIssuemetaData(Map<String, CimFieldInfo> fields) {
        this.fields = fields;
    }


    public Map<String, CimFieldInfo> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("fields", this.fields).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CimEditIssuemetaData that = (CimEditIssuemetaData) o;
        return java.util.Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(fields);
    }
}
