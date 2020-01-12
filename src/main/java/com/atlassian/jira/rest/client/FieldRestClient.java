package com.atlassian.jira.rest.client;

import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.domain.User;
import com.sun.istack.NotNull;

import java.net.URI;
import java.util.List;

public interface FieldRestClient {

    List<Field> getFields(ProgressMonitor progressMonitor);

}