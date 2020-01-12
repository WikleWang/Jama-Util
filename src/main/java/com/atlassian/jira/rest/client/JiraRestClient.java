//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.atlassian.jira.rest.client;

import com.sun.jersey.client.apache.ApacheHttpClient;

public interface JiraRestClient {

    IssueRestClient getIssueClient();

    SessionRestClient getSessionClient();

    UserRestClient getUserClient();

    GroupRestClient getGroupClient();

    ProjectRestClient getProjectClient();

    ComponentRestClient getComponentClient();

    MetadataRestClient getMetadataClient();

    SearchRestClient getSearchClient();

    VersionRestClient getVersionRestClient();

    ProjectRolesRestClient getProjectRolesRestClient();

    ApacheHttpClient getTransportClient();

    FieldRestClient getFieldRestField();
}
