package com.atlassian.jira.rest.client.domain;
import com.google.common.base.Objects;
import java.util.List;

public class BulkIssues {

    private List<BasicIssue> issues;

    private List<String> errors;

    public BulkIssues(List<BasicIssue> issues, List<String> errors) {
        this.issues = issues;
        this.errors = errors;
    }

    public List<BasicIssue> getIssues() {
        return issues;
    }

    public void setIssues(List<BasicIssue> issues) {
        this.issues = issues;
    }


    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this).add("issues", this.issues).add("errors", this.errors).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulkIssues that = (BulkIssues) o;
        return java.util.Objects.equals(issues, that.issues) &&
                java.util.Objects.equals(errors, that.errors);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.issues, this.errors});
    }

}
