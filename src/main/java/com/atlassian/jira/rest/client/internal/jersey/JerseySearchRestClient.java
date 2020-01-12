
package com.atlassian.jira.rest.client.internal.jersey;

import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.ProgressMonitor;
import com.atlassian.jira.rest.client.RestClientException;
import com.atlassian.jira.rest.client.SearchRestClient;
import com.atlassian.jira.rest.client.IssueRestClient.Expandos;
import com.atlassian.jira.rest.client.domain.FavouriteFilter;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.json.FavouriteFilterJsonParser;
import com.atlassian.jira.rest.client.internal.json.GenericJsonArrayParser;
import com.atlassian.jira.rest.client.internal.json.SearchResultJsonParser;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.sun.jersey.client.apache.ApacheHttpClient;

import java.net.URI;
import java.util.*;
import javax.annotation.Nullable;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JerseySearchRestClient extends AbstractJerseyRestClient implements SearchRestClient {
    private static final EnumSet<Expandos> DEFAULT_EXPANDS;
    private static final Function<Expandos, String> EXPANDO_TO_PARAM;
    private static final String DEFAULT_FIELDS = "project,issuetype,summary,created,updated,status";
    private static final String EXPAND_ATTRIBUTE = "expand";
    private static final String FIELDS_ATTRIBUTE = "fields";
    private static final String START_AT_ATTRIBUTE = "startAt";
    private static final String MAX_RESULTS_ATTRIBUTE = "maxResults";
    private static final int MAX_JQL_LENGTH_FOR_HTTP_GET = 500;
    private static final String JQL_ATTRIBUTE = "jql";
    private final SearchResultJsonParser keyOnlySearchResultJsonParser = new SearchResultJsonParser(false);
    private final SearchResultJsonParser fullSearchResultJsonParser = new SearchResultJsonParser(true);
    private final GenericJsonArrayParser<FavouriteFilter> favouriteFiltersJsonParser = GenericJsonArrayParser.create(new FavouriteFilterJsonParser());
    private static final String SEARCH_URI_PREFIX = "search";
    private final URI searchUri;

    public JerseySearchRestClient(URI baseUri, ApacheHttpClient client) {
        super(baseUri, client);
        this.searchUri = UriBuilder.fromUri(baseUri).path(SEARCH_URI_PREFIX).build(new Object[0]);
    }

    public SearchResult searchJql(@Nullable String jql, @Nullable String fields, ProgressMonitor progressMonitor) {
        return this.searchJqlImpl(jql, fields, (Integer) null, (Integer) null, progressMonitor, this.keyOnlySearchResultJsonParser);
    }

    public SearchResult searchJqlWithFullIssues(@Nullable String jql, @Nullable String fields, ProgressMonitor progressMonitor) {
        return this.searchJqlImpl(jql, fields, (Integer) null, (Integer) null, progressMonitor, this.fullSearchResultJsonParser);
    }

    public SearchResult searchJql(@Nullable String jql, @Nullable String fields, int maxResults, int startAt, ProgressMonitor progressMonitor) {
        return this.searchJqlImpl(jql, fields, maxResults, startAt, progressMonitor, this.keyOnlySearchResultJsonParser);
    }

    public SearchResult searchJqlWithFullIssues(@Nullable String jql, @Nullable String fields, int maxResults, int startAt, ProgressMonitor progressMonitor) {
        return this.searchJqlImpl(jql, fields, maxResults, startAt, progressMonitor, this.fullSearchResultJsonParser);
    }

    private SearchResult searchJqlImpl(@Nullable String jql, @Nullable String fields, Integer maxResults, Integer startAt, ProgressMonitor progressMonitor, SearchResultJsonParser parser) {
        if (jql == null) {
            jql = "";
        }
        UriBuilder uriBuilder;

        if (fields != null && fields.length() > 0) {
            Set<String> fieldList = new HashSet<String>(Arrays.asList(DEFAULT_FIELDS.split(",")));
            fieldList.addAll(Arrays.asList(fields.split(",")));
            StringBuffer buffer = new StringBuffer();
            fieldList.forEach(f -> buffer.append(buffer.length() > 0 ? "," + f : f));
            fields = buffer.toString();
        } else {
            fields = DEFAULT_FIELDS;
        }

        if (jql.length() > MAX_JQL_LENGTH_FOR_HTTP_GET) {
            uriBuilder = UriBuilder.fromUri(this.searchUri);
            JSONObject postEntity = new JSONObject();
            try {
                postEntity.put(JQL_ATTRIBUTE, jql);
                if (maxResults != null && startAt != null) {
                    postEntity.put(START_AT_ATTRIBUTE, startAt);
                    postEntity.put(MAX_RESULTS_ATTRIBUTE, maxResults);
                }
                postEntity.put(FIELDS_ATTRIBUTE, fields);
                uriBuilder = uriBuilder.queryParam(EXPAND_ATTRIBUTE, new Object[]{Joiner.on(',').join(Iterables.transform(DEFAULT_EXPANDS, EXPANDO_TO_PARAM))});
            } catch (JSONException var9) {
                throw new RestClientException(var9);
            }
            return (SearchResult) this.postAndParse(uriBuilder.build(new Object[0]), postEntity, parser, progressMonitor);
        } else {
            uriBuilder = UriBuilder.fromUri(this.searchUri).queryParam(JQL_ATTRIBUTE, new Object[]{jql});
            if (maxResults != null && startAt != null) {
                uriBuilder = uriBuilder.queryParam(MAX_RESULTS_ATTRIBUTE, new Object[]{maxResults}).queryParam(START_AT_ATTRIBUTE, new Object[]{startAt});
            }
            URI uri = uriBuilder.queryParam(EXPAND_ATTRIBUTE, new Object[]{Joiner.on(',').join(Iterables.transform(DEFAULT_EXPANDS, EXPANDO_TO_PARAM))}).queryParam(FIELDS_ATTRIBUTE, new Object[]{fields}).build(new Object[0]);
            return (SearchResult) this.getAndParse(uri, parser, progressMonitor);
        }
    }

    public Iterable<FavouriteFilter> getFavouriteFilters(NullProgressMonitor progressMonitor) {
        URI uri = UriBuilder.fromUri(this.baseUri).path("filter/favourite").build(new Object[0]);
        return (Iterable) this.getAndParse(uri, this.favouriteFiltersJsonParser, progressMonitor);
    }

    static {
        DEFAULT_EXPANDS = EnumSet.of(Expandos.NAMES, Expandos.SCHEMA);
        EXPANDO_TO_PARAM = new Function<Expandos, String>() {
            public String apply(Expandos from) {
                return from.name().toLowerCase();
            }
        };
    }
}