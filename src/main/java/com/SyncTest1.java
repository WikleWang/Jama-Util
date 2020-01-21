package com;

import com.alibaba.fastjson.JSONObject;
import com.amt.jama.connect.constants.HttpConstants;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.items.Item;
import com.amt.jama.core.po.location.Location;
import com.amt.jama.core.po.parent.Parent;
import com.amt.jama.core.po.request.RequestItem;
import com.amt.jama.util.jama.ItemClient;
import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.domain.input.FieldInput;
import com.atlassian.jira.rest.client.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

import java.io.InputStream;
import java.net.URI;
import java.util.*;

public class SyncTest1 {
    static JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
    static JiraRestClient restClient = null;
    static NullProgressMonitor progressMonitor = new NullProgressMonitor();


    private static HttpPoolUtils poolUtils = null;
    private static Map<String, String> headers = new HashMap<>();
    private static com.amt.jama.util.jama.JamaInstance jamaRestInstance = null;
    private static ItemClient itemClient = null;

    public static void main(String[] args) {


        Properties jira = new Properties();
        InputStream inputStream = Object.class.getResourceAsStream("/jira.properties");
        try {
            jira.load(inputStream);
            URI uri = new URI(jira.getProperty("url"));
            restClient = factory.createWithBasicHttpAuthentication(uri, jira.getProperty("username"), jira.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Properties jama = new Properties();
        inputStream = Object.class.getResourceAsStream("/jama.properties");
        try {
            jama.load(inputStream);
            jamaRestInstance = new com.amt.jama.util.jama.JamaInstance(jama.getProperty("url"), jama.getProperty("username"),
                    jama.getProperty("password"), "/rest/v1");
            // set authorization
            headers.put(HttpConstants.AUTHORIZATION, HttpConstants.BASIC + Base64.getEncoder().encodeToString((jama.getProperty("username") + ":" + jama.getProperty("password")).getBytes()));
//        // init http pool
            poolUtils = HttpPoolUtils.getInstance(jama.getProperty("url"));
            itemClient = new ItemClient("/rest/v1");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<>();


        new Timer("testTimer").schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    // JAMA to JIRA
                    IssueRestClient issueClient = restClient.getIssueClient();

                    SearchResult searchResult = restClient.getSearchClient().searchJqlWithFullIssues("project = JRA and issuetype = 'Story' and 'Integration ID' is  EMPTY", "summary", progressMonitor);
                    if(searchResult!=null && searchResult.getIssues()!=null) {
                        Iterator<? extends BasicIssue> iterator = searchResult.getIssues().iterator();
                        while (iterator.hasNext()) {
                            Issue next = (Issue) iterator.next();
                            System.out.println(next);

                            String summary = next.getSummary();

                            RequestItem item = new RequestItem();
                            Location location = new Location();
                            Parent parent = new Parent();
                            parent.setItem(2754);
                            //parent.setProject(54);
                            location.setParent(parent);
                            item.setLocation(location);
                            Map<String, Object> fields = new HashMap<>();
                            fields.put("summary",summary);
                            fields.put("name",summary);
                            fields.put("integration_url$156",jira.getProperty("url") + "/browse/" + next.getKey());
                            fields.put("integration_id$156", next.getKey());
                            item.setFields(fields);
                            item.setItemType(156);

                            JSONObject item1 = itemClient.createItem(item, poolUtils, headers);
                            Integer jamaId = item1.getJSONObject("meta").getInteger("id");
                            List<FieldInput> fieldInputs = new ArrayList<>();
                            Item item2 = jamaRestInstance.getItem(jamaId, false);
                            fieldInputs.add(new FieldInput("customfield_10300",item2.getFields().get("documentKey")));
                            String url = jama.getProperty("url") + "/perspective.req#/items/" + jamaId + "?projectId=54";
                            fieldInputs.add(new FieldInput("customfield_10201",url));
                            restClient.getIssueClient().updateByKey(next.getKey(),fieldInputs, progressMonitor);

                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        }, 1000, 1000 * 60);
    }

}
