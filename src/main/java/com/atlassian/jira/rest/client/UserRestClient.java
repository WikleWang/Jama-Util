package com.atlassian.jira.rest.client;

import com.atlassian.jira.rest.client.domain.User;
import com.sun.istack.NotNull;

import java.net.URI;
import java.util.List;

public interface UserRestClient {

    User getUser(String username, ProgressMonitor progressMonitor);

    User getUser(URI userUri, ProgressMonitor progressMonitor);

    List<User> searchUser(@NotNull String username, Integer maxResults, Integer startAt, ProgressMonitor progressMonitor);

}