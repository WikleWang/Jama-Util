package com.atlassian.jira.rest.client.domain;

import com.google.common.base.Objects;

public class Schema {

    private final String type;

    private final String system;

    private final String items;

    private final String custom;

    private final Integer customId;

    public Schema(String type, String system, String items, String custom, Integer customId) {
        this.type = type;
        this.system = system;
        this.items = items;
        this.custom = custom;
        this.customId = customId;
    }


    public String getType() {
        return type;
    }

    public String getSystem() {
        return system;
    }

    public String getItems() {
        return items;
    }

    public String getCustom() {
        return custom;
    }

    public Integer getCustomId() {
        return customId;
    }


    public String toString() {
        return Objects.toStringHelper(this).add("type", this.type)
                .add("system", this.system)
                .add("items", this.items)
                .add("custom", this.custom)
                .add("customId", this.customId)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return java.util.Objects.equals(type, schema.type) &&
                java.util.Objects.equals(system, schema.system) &&
                java.util.Objects.equals(items, schema.items) &&
                java.util.Objects.equals(custom, schema.custom) &&
                java.util.Objects.equals(customId, schema.customId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(type, system, items, custom, customId);
    }
}
