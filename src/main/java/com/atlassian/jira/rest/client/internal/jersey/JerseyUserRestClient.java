package com.atlassian.jira.rest.client.internal.jersey;

import com.atlassian.jira.rest.client.MetadataRestClient;
import com.atlassian.jira.rest.client.ProgressMonitor;
import com.atlassian.jira.rest.client.UserRestClient;
import com.atlassian.jira.rest.client.domain.ServerInfo;
import com.atlassian.jira.rest.client.domain.User;
import com.atlassian.jira.rest.client.internal.json.GroupsJsonParser;
import com.atlassian.jira.rest.client.internal.json.MemberJsonParser;
import com.atlassian.jira.rest.client.internal.json.UserJsonParser;
import com.atlassian.jira.rest.client.internal.json.UsersJsonParser;
import com.atlassian.jira.rest.client.internal.json.gen.CommentJsonGenerator;
import com.atlassian.jira.rest.client.internal.json.gen.GroupInputGenerator;
import com.atlassian.jira.rest.client.internal.json.gen.LinkIssuesInputGenerator;
import com.sun.jersey.client.apache.ApacheHttpClient;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Callable;
import javax.ws.rs.core.UriBuilder;

public class JerseyUserRestClient extends AbstractJerseyRestClient implements UserRestClient {
    private static final String USER_URI_PREFIX = "user";
    private static final String MAX_RESULTS_ATTRIBUTE = "maxResults";
    private static final Integer DEFAULT_START_AT = 0;
    private static final Integer DEFAULT_MAX_RESULTS = 200;
    private static final String START_AT_ATTRIBUTE = "startAt";
    private static final String USERNAME_ATTRIBUTE = "username";
    private static final String QUERY_ATTRIBUTE = "query";
    private final UserJsonParser userJsonParser = new UserJsonParser();
    private final UsersJsonParser usersJsonParser = new UsersJsonParser();
    private final GroupsJsonParser groupsJsonParser = new GroupsJsonParser();
    private final MemberJsonParser memberJsonParser = new MemberJsonParser();

    public JerseyUserRestClient(URI baseUri, ApacheHttpClient client) {
        super(baseUri, client);
    }

    public User getUser(String username, ProgressMonitor progressMonitor) {
        URI userUri = UriBuilder.fromUri(this.baseUri).path("user").queryParam(USERNAME_ATTRIBUTE, new Object[]{username}).queryParam("expand", new Object[]{"groups"}).build(new Object[0]);
        return this.getUser(userUri, progressMonitor);
    }

    public User getUser(URI userUri, ProgressMonitor progressMonitor) {
        return (User) this.getAndParse(userUri, this.userJsonParser, progressMonitor);
    }

    @Override
    public List<User> searchUser(String username, Integer maxResults, Integer startAt, ProgressMonitor progressMonitor) {
        UriBuilder uri = UriBuilder.fromUri(this.baseUri).path("user/search").queryParam(USERNAME_ATTRIBUTE, new Object[]{username});
        URI userUri = setLimit(uri, maxResults, startAt);
        return (List<User>) this.getAndParse(userUri, this.usersJsonParser, progressMonitor);
    }


    private URI setLimit(UriBuilder builder, Integer maxResults, Integer startAt) {
        if (maxResults == null) maxResults = DEFAULT_MAX_RESULTS;
        if (startAt == null) startAt = DEFAULT_START_AT;
        return builder.queryParam(MAX_RESULTS_ATTRIBUTE, new Object[]{maxResults}).queryParam(START_AT_ATTRIBUTE, new Object[]{startAt}).build(new Object[0]);
    }


}