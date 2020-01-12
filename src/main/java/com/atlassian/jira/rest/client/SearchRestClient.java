package com.atlassian.jira.rest.client;

import com.atlassian.jira.rest.client.domain.FavouriteFilter;
import com.atlassian.jira.rest.client.domain.SearchResult;
import javax.annotation.Nullable;

public interface SearchRestClient {
    SearchResult searchJql(@Nullable String jql, @Nullable String fields, ProgressMonitor progressMonitor);

    SearchResult searchJqlWithFullIssues(@Nullable String jql, @Nullable String fields, ProgressMonitor progressMonitor);

    SearchResult searchJql(@Nullable String jql, @Nullable String fields, int maxResults, int startAt, ProgressMonitor progressMonitor);

    SearchResult searchJqlWithFullIssues(@Nullable String jql, @Nullable String fields, int maxResults, int startAt, ProgressMonitor progressMonitor);

    Iterable<FavouriteFilter> getFavouriteFilters(NullProgressMonitor nullProgressMonitor);
}