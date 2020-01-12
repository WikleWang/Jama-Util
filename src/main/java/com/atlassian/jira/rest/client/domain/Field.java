package com.atlassian.jira.rest.client.domain;

import java.util.List;
import java.util.Objects;

public class Field {
    private final String id;

    private final String name;

    private final Boolean custom;

    private final Boolean orderable;

    private final Boolean navigable;

    private final Boolean searchable;

    private final List<String> clauseNames;

    private final Schema schema;


    public Field(String id, String name, Boolean custom, Boolean orderable, Boolean navigable, Boolean searchable, List<String> clauseNames, Schema schema) {
        this.id = id;
        this.name = name;
        this.custom = custom;
        this.orderable = orderable;
        this.navigable = navigable;
        this.searchable = searchable;
        this.clauseNames = clauseNames;
        this.schema = schema;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getCustom() {
        return custom;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public Boolean getNavigable() {
        return navigable;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public List<String> getClauseNames() {
        return clauseNames;
    }

    public Schema getSchema() {
        return schema;
    }

    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("id", this.id)
                .add("name", this.name)
                .add("custom", this.custom)
                .add("orderable", this.orderable)
                .add("navigable", this.navigable)
                .add("searchable", this.searchable)
                .add("clauseNames", this.clauseNames)
                .add("schema", this.schema)
                .toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Objects.equals(id, field.id) &&
                Objects.equals(name, field.name) &&
                Objects.equals(custom, field.custom) &&
                Objects.equals(orderable, field.orderable) &&
                Objects.equals(navigable, field.navigable) &&
                Objects.equals(searchable, field.searchable) &&
                Objects.equals(clauseNames, field.clauseNames) &&
                Objects.equals(schema, field.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, custom, orderable, navigable, searchable, clauseNames, schema);
    }
}
