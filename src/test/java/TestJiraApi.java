import com.atlassian.jira.rest.client.*;
import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.domain.input.*;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.json.CimEditIssuemetaData;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class TestJiraApi {


    static Logger logger = Logger.getLogger(TestJiraApi.class);

    static JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();

    static JiraRestClient restClient = null;

    static NullProgressMonitor progressMonitor = new NullProgressMonitor();

    static String url = "http://localhost:8080";

    static String username = "wikle.wang";

    static String password = "Ww920109@qqcom";

    @Before
    public void init() throws Exception {
        URI uri = null;
        try {
            uri = new URI(url);
            restClient = factory.createWithBasicHttpAuthentication(uri, username, password);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCreateIssue() {
        IssueRestClient issueClient = restClient.getIssueClient();
        IssueInputBuilder issueBuilder = new IssueInputBuilder("JRA",10001L);
        issueBuilder.setFieldValue("summary","Test create issue by java rest api");
        BasicIssue issue = issueClient.createIssue(issueBuilder.build(), progressMonitor);
        logger.info(issue);
    }

    @Test
    public void testCreateIssues() {
        IssueRestClient issueClient = restClient.getIssueClient();
        IssueInputBuilder issueBuilder = new IssueInputBuilder("JRA",10001L);
        issueBuilder.setFieldValue("summary","Test create bulk issue one by java rest api");

        IssueInputBuilder issueBuilder2 = new IssueInputBuilder("JRA",10001L);
        issueBuilder2.setFieldValue("summary","Test create bulk issue two by java rest api ");

        BulkIssueInput issues = new BulkIssueInput(Arrays.asList(issueBuilder.build(),issueBuilder2.build()));
        BulkIssues bulkIssue = issueClient.createIssues(issues, progressMonitor);
        logger.info(bulkIssue);
    }

    @Test
    public void testGetCreateMetaData() {

        List<Field> fields = restClient.getFieldRestField().getFields(progressMonitor);
        System.out.println(fields);
        IssueRestClient issueClient = restClient.getIssueClient();
        Iterable<CimProject> createIssueMetadata = issueClient.getCreateIssueMetadata(null, progressMonitor);

        logger.info(createIssueMetadata);
        List<String> expandos = Arrays.asList("projects.issuetypes.fields");
        List<Long> projectIds = Arrays.asList(10300L);
        List<String> projectKeys = Arrays.asList();
        List<Long> issueTypeIds = Arrays.asList(10001L);
        List<String> issueTypeNames = Arrays.asList();
        GetCreateIssueMetadataOptions options = new GetCreateIssueMetadataOptions(expandos, issueTypeNames, issueTypeIds, projectKeys, projectIds);

        Iterable<CimProject> createIssueMetadata1 = issueClient.getCreateIssueMetadata(options, progressMonitor);
        logger.info(createIssueMetadata1);
    }


    @Test
    public void testGetIssue() {
        IssueRestClient issueClient = restClient.getIssueClient();
        Issue issue =  issueClient.getIssue("JRA-1", progressMonitor);
        logger.info(issue);
        Issue summary = issueClient.getIssue("JRA-1", "summary", progressMonitor);
        System.out.println(summary);

        Watchers watchers = issueClient.getWatchers("JRA-1", progressMonitor);
        System.out.println(watchers);

        System.out.println(issueClient.getVotes("JRA-1", progressMonitor));

        issueClient.vote("JRA-1",progressMonitor);
        issueClient.unvote("JRA-1",progressMonitor);
        issueClient.unwatch("JRA-1",progressMonitor);
        issueClient.watch("JRA-1",progressMonitor);

    }

    @Test
    public void testUpdateIssue() {
        IssueRestClient issueClient = restClient.getIssueClient();
        List<FieldInput> fieldInputs = new ArrayList<>();
        fieldInputs.add(new FieldInput("summary","Test create bulk issue one by java rest api, and then update!"));
        issueClient.updateByKey("JRA-1",fieldInputs, progressMonitor);
    }

    @Test
    public void testRemoveIssue() {
        IssueRestClient issueClient = restClient.getIssueClient();
        CimEditIssuemetaData editIssueMetadata = issueClient.getEditIssueMetadata("JRA-1", progressMonitor);
        System.out.println(editIssueMetadata);

    }

    @Test
    public void testTransition() {
        IssueRestClient issueClient = restClient.getIssueClient();
//        Issue issue = issueClient.getIssue("JRA-1", progressMonitor);
//        URI transitionsUri = issue.getTransitionsUri();
//        Iterable<Transition> transitions = issueClient.getTransitions(issue, progressMonitor);
//        System.out.println(transitions);

        Iterable<Transition> transitions = issueClient.getTransitions("JRA-1", progressMonitor);
        System.out.println(true);

        issueClient.transition("JRA-1", "Doneed", progressMonitor);
    }

    @Test
    public void testLinkIssue() {
        IssueRestClient issueClient = restClient.getIssueClient();
        LinkIssuesInput linkIssue = new LinkIssuesInput("JRA-1","JRA-3","Relates");
        issueClient.linkIssue(linkIssue, progressMonitor);
        //issueClient.unlinkIssue(URI.create("http://localhost:8080/rest/api/2/issueLink/10100"), progressMonitor);
        Issue issuelinks = issueClient.getIssue("JRA-1", "issuelinks", progressMonitor);
        System.out.println(issuelinks);

        Iterable<IssueLink> issueLinks = issueClient.getIssueLinks("JRA-1", progressMonitor);
        System.out.println(issueLinks);
    }

    @Test
    public void testAddAttachment() throws Exception{
        IssueRestClient issueClient = restClient.getIssueClient();
        AttachmentInput attachmentInput = new AttachmentInput("激活码1", new FileInputStream("/Users/wikle.wang/Code/激活码在文本里售后微信yingke5858.rtf"));
        AttachmentInput attachmentInput1 = new AttachmentInput("激活码2", new FileInputStream("/Users/wikle.wang/Code/激活码在文本里售后微信yingke5858.rtf"));
        //issueClient.addAttachments(progressMonitor, "JRA-1",attachmentInput, attachmentInput1);
        issueClient.removeAttachment(progressMonitor, 10200);

    }

    @Test
    public void testAddComment() throws Exception{
        IssueRestClient issueClient = restClient.getIssueClient();
        Comment comment = Comment.valueOf("Add Comment!");
        //issueClient.addComment(progressMonitor, "JRA-1", comment);
        //issueClient.removeComment(progressMonitor, "JRA-1",10201);
        //comment = Comment.valueOf("new comment");
        //Comment new_comment = new Comment(null, "new comment", null, null, null, null, null, 10202L);
        //issueClient.updateComment(progressMonitor, "JRA-1", new_comment);
        InputStream attachment = issueClient.getAttachment(progressMonitor, URI.create("http://localhost:8080/rest/api/2/attachment/10201"));
        System.out.println(attachment);

        restClient.getSearchClient();
    }


    @Test
    public void testJQL() throws Exception{
        SearchRestClient searchClient = restClient.getSearchClient();
        //SearchResult searchResult = searchClient.searchJqlWithFullIssues("project = JRA", "summary",progressMonitor);
        //System.out.println(searchResult);
        //User user = restClient.getUserClient().getUser("wikle.wang", progressMonitor);
        //System.out.println(user);
//        List<User> users = restClient.getUserClient().searchUser("J", 30, 0, progressMonitor);
//        System.out.println(users);
//        List<String> admin = restClient.getGroupClient().searchGroup("admin", 30, 0, progressMonitor);
//        System.out.println(admin);
//        List<User> members = restClient.getGroupClient().getMembers("jira-administrators", 30, 0, progressMonitor);
//        System.out.println(members);

        //restClient.getGroupClient().addGroup("Rest-Java-API-Group", progressMonitor);

        //restClient.getGroupClient().removeGroup("Rest-Java-API-Group", progressMonitor);
        //restClient.getGroupClient().removeUser("Rest-API-Group","amt.jama", progressMonitor);
        //restClient.getGroupClient().addUser("Rest-API-Group","amt.jama", progressMonitor);
        GroupUserPicker w = restClient.getGroupClient().getGroupUserPicker("w", 10, 0, null, null, progressMonitor);
        System.out.println(w);
    }















}