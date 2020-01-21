package com.amt.jama.util.jama;

/**
 * @author wikle.wang
 */
public class JamaUtil {

    private String restVersion = "/rest/v1";

    private AbstractClient abstractClient;

    private ActivityClient activityClient;

    private AttachmentClient attachmentClient;

    private BaselineClient baselineClient;

    private CommentClient commentClient;

    private FileClient fileClient;

    private FilterClient filterClient;

    private ItemClient itemClient;

    private ItemTypeClient itemTypeClient;

    private PickListClient pickListClient;

    private ProjectClient projectClient;

    private RelationshipClient relationshipClient;

    private ReleaseClient releaseClient;

    private SystemClient systemClient;

    private TagClient tagClient;

    private TestCycleClient testCycleClient;

    private TestPlanClient testPlanClient;

    private TestRunClient testRunClient;

    private UserGroupClient userGroupClient;

    private UserClient userClient;

    public void setRestVersion(String restVersion) {
        this.restVersion = restVersion;
    }

    public String getRestVersion() {
        return restVersion;
    }

    AbstractClient getAbstractClient() {
        return abstractClient == null ? new AbstractClient(this.restVersion) : abstractClient;
    }

    ActivityClient getActivityClient() {
        return activityClient == null ? new ActivityClient(this.restVersion) : activityClient;
    }

    AttachmentClient getAttachmentClient() {
        return attachmentClient == null ? new AttachmentClient(this.restVersion) : attachmentClient;
    }

    BaselineClient getBaselineClient() {
        return baselineClient == null ? new BaselineClient(this.restVersion) : baselineClient;
    }

    CommentClient getCommentClient() {
        return commentClient == null ? new CommentClient(this.restVersion) : commentClient;
    }

    FileClient getFileClient() {
        return fileClient == null ? new FileClient(this.restVersion) : fileClient;
    }

    FilterClient getFilterClient() {
        return filterClient == null ? new FilterClient(this.restVersion) : filterClient;
    }

    ItemClient getItemClient() {
        return itemClient == null ? new ItemClient(this.restVersion) : itemClient;
    }

    ItemTypeClient getItemTypeClient() {
        return itemTypeClient == null ? new ItemTypeClient(this.restVersion) : itemTypeClient;
    }

    PickListClient getPickListClient() {
        return pickListClient == null ? new PickListClient(this.restVersion) : pickListClient;
    }

    ProjectClient getProjectClient() {
        return projectClient == null ? new ProjectClient(this.restVersion) : projectClient;
    }

   RelationshipClient getRelationshipClient() {
        return relationshipClient == null ? new RelationshipClient(this.restVersion) : relationshipClient;
    }

    ReleaseClient getReleaseClient() {
        return releaseClient == null ? new ReleaseClient(this.restVersion) : releaseClient;
    }

    SystemClient getSystemClient() {
        return systemClient == null ? new SystemClient(this.restVersion) : systemClient;
    }

    TagClient getTagClient() {
        return tagClient == null ? new TagClient(this.restVersion) : tagClient;
    }

    TestCycleClient getTestCycleClient() {
        return testCycleClient == null ? new TestCycleClient(this.restVersion) : testCycleClient;
    }

    TestPlanClient getTestPlanClient() {
        return testPlanClient == null ? new TestPlanClient(this.restVersion) : testPlanClient;
    }

    TestRunClient getTestRunClient() {
        return testRunClient == null ? new TestRunClient(this.restVersion) : testRunClient;
    }

    UserGroupClient getUserGroupClient() {
        return userGroupClient == null ? new UserGroupClient(this.restVersion) : userGroupClient;
    }

    UserClient getUserClient() {
        return userClient == null ? new UserClient(this.restVersion) : userClient;
    }

    private static class SingletonHolder {
        private static final JamaUtil INSTANCE = new JamaUtil();
    }

    public static JamaUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }


}
