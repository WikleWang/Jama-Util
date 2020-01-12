package com.atlassian.jira.rest.client.internal.jersey;

import com.atlassian.jira.rest.client.FieldRestClient;
import com.atlassian.jira.rest.client.ProgressMonitor;
import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.internal.json.FieldJsonParser;
import com.sun.jersey.client.apache.ApacheHttpClient;

import java.net.URI;
import java.util.List;

public class JerseyFieldRestClient extends AbstractJerseyRestClient implements FieldRestClient {

    private final FieldJsonParser fieldJsonParser = new FieldJsonParser();

    public JerseyFieldRestClient(URI baseUri, ApacheHttpClient client) {
        super(baseUri, client);
    }

    @Override
    public List<Field> getFields(ProgressMonitor progressMonitor) {
        return (List<Field>) this.getAndParse(URI.create(baseUri + "/field"), this.fieldJsonParser, progressMonitor);
    }
}