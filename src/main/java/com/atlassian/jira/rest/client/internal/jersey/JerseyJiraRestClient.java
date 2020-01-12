//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.atlassian.jira.rest.client.internal.jersey;

import com.atlassian.jira.rest.client.*;
import com.sun.jersey.api.client.AsyncViewResource;
import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.ViewResource;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.ApacheHttpClientHandler;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

public class JerseyJiraRestClient implements JiraRestClient {
    private final URI baseUri;
    private final IssueRestClient issueRestClient;
    private final SessionRestClient sessionRestClient;
    private final UserRestClient userRestClient;
    private final GroupRestClient groupRestClient;
    private final ProjectRestClient projectRestClient;
    private final ComponentRestClient componentRestClient;
    private final MetadataRestClient metadataRestClient;
    private final SearchRestClient searchRestClient;
    private final VersionRestClient versionRestClient;
    private final ProjectRolesRestClient projectRolesRestClient;
    private final ApacheHttpClient client;
    private final FieldRestClient fieldRestClient;

    public JerseyJiraRestClient(URI serverUri, final AuthenticationHandler authenticationHandler) {
        this.baseUri = UriBuilder.fromUri(serverUri).path("/rest/api/latest").build(new Object[0]);
        DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig();
        authenticationHandler.configure(config);
        this.client = new ApacheHttpClient(createDefaultClientHander(config)) {
            public WebResource resource(URI u) {
                WebResource resource = super.resource(u);
                authenticationHandler.configure(resource, this);
                return resource;
            }

            public AsyncWebResource asyncResource(URI u) {
                AsyncWebResource resource = super.asyncResource(u);
                authenticationHandler.configure(resource, this);
                return resource;
            }

            public ViewResource viewResource(URI u) {
                ViewResource resource = super.viewResource(u);
                authenticationHandler.configure(resource, this);
                return resource;
            }

            public AsyncViewResource asyncViewResource(URI u) {
                AsyncViewResource resource = super.asyncViewResource(u);
                authenticationHandler.configure(resource, this);
                return resource;
            }
        };
        this.metadataRestClient = new JerseyMetadataRestClient(this.baseUri, this.client);
        this.sessionRestClient = new JerseySessionRestClient(this.client, serverUri);
        this.issueRestClient = new JerseyIssueRestClient(this.baseUri, this.client, this.sessionRestClient, this.metadataRestClient);
        this.userRestClient = new JerseyUserRestClient(this.baseUri, this.client);
        this.groupRestClient = new JerseyGroupRestClient(this.baseUri, this.client);
        this.projectRestClient = new JerseyProjectRestClient(this.baseUri, this.client);
        this.componentRestClient = new JerseyComponentRestClient(this.baseUri, this.client);
        this.searchRestClient = new JerseySearchRestClient(this.baseUri, this.client);
        this.versionRestClient = new JerseyVersionRestClient(this.baseUri, this.client);
        this.projectRolesRestClient = new JerseyProjectRolesRestClient(this.baseUri, this.client, serverUri);
        this.fieldRestClient = new JerseyFieldRestClient(this.baseUri, this.client);
    }

    @Override
    public IssueRestClient getIssueClient() {
        return this.issueRestClient;
    }

    @Override
    public SessionRestClient getSessionClient() {
        return this.sessionRestClient;
    }

    @Override
    public UserRestClient getUserClient() {
        return this.userRestClient;
    }

    @Override
    public GroupRestClient getGroupClient() {
        return this.groupRestClient;
    }

    @Override
    public ProjectRestClient getProjectClient() {
        return this.projectRestClient;
    }

    @Override
    public ComponentRestClient getComponentClient() {
        return this.componentRestClient;
    }

    @Override
    public MetadataRestClient getMetadataClient() {
        return this.metadataRestClient;
    }

    @Override
    public SearchRestClient getSearchClient() {
        return this.searchRestClient;
    }

    @Override
    public VersionRestClient getVersionRestClient() {
        return this.versionRestClient;
    }

    @Override
    public ProjectRolesRestClient getProjectRolesRestClient() {
        return this.projectRolesRestClient;
    }

    @Override
    public ApacheHttpClient getTransportClient() {
        return this.client;
    }

    @Override
    public FieldRestClient getFieldRestField() {
        return this.fieldRestClient;
    }

    private static ApacheHttpClientHandler createDefaultClientHander(DefaultApacheHttpClientConfig config) {
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        return new ApacheHttpClientHandler(client, config);
    }
}
