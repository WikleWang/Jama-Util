package com.atlassian.jira.rest.client;

import com.atlassian.jira.rest.client.domain.GroupUserPicker;
import com.atlassian.jira.rest.client.domain.User;
import com.sun.istack.NotNull;

import java.util.List;

public interface GroupRestClient {

    List<String> searchGroup(@NotNull String query, Integer maxResults, Integer startAt, ProgressMonitor progressMonitor);

    List<User> getMembers(@NotNull String groupName, Integer maxResults, Integer startAt, ProgressMonitor progressMonitor);

    void addGroup(@NotNull String groupName, ProgressMonitor progressMonitor);

    void removeGroup(@NotNull String groupName, ProgressMonitor progressMonitor);

    void addUser(@NotNull String groupName, @NotNull String name, ProgressMonitor progressMonitor);

    void removeUser(@NotNull String groupName, @NotNull String username, ProgressMonitor progressMonitor);

    GroupUserPicker getGroupUserPicker(@NotNull String query, Integer maxResults, Integer startAt, Iterable<String> projectId, Iterable<String> issueTypeId, ProgressMonitor progressMonitor);


}