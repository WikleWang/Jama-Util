package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpConstants;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.field.*;
import com.amt.jama.core.fieldvalue.*;
import com.amt.jama.core.po.abstractitem.AbstractItem;
import com.amt.jama.core.po.activities.Activity;
import com.amt.jama.core.po.activities.ActivityDataListWrapper;
import com.amt.jama.core.po.attachments.Attachment;
import com.amt.jama.core.po.attachments.AttachmentDataListWrapper;
import com.amt.jama.core.po.comments.Comment;
import com.amt.jama.core.po.comments.CommentDataListWrapper;
import com.amt.jama.core.po.filters.Filter;
import com.amt.jama.core.po.filters.FilterDataListWrapper;
import com.amt.jama.core.po.items.Item;
import com.amt.jama.core.po.items.ItemDataListWrapper;
import com.amt.jama.core.po.items.ItemDataWrapper;
import com.amt.jama.core.po.itemtypes.ItemType;
import com.amt.jama.core.po.itemtypes.ItemTypeDataListWrapper;
import com.amt.jama.core.po.itemtypes.ItemTypeDataWrapper;
import com.amt.jama.core.po.itemtypes.ItemTypeField;
import com.amt.jama.core.po.link.Link;
import com.amt.jama.core.po.link.LinkDataListWrapper;
import com.amt.jama.core.po.link.LinkDataWrapper;
import com.amt.jama.core.po.location.Location;
import com.amt.jama.core.po.location.LocationDataWrapper;
import com.amt.jama.core.po.lock.Lock;
import com.amt.jama.core.po.lock.LockDataWrapper;
import com.amt.jama.core.po.picklistoptions.PickListOption;
import com.amt.jama.core.po.picklistoptions.PickListOptionDataListWrapper;
import com.amt.jama.core.po.picklistoptions.PickListOptionDataWrapper;
import com.amt.jama.core.po.picklists.PickList;
import com.amt.jama.core.po.picklists.PickListDataListWrapper;
import com.amt.jama.core.po.picklists.PickListDataWrapper;
import com.amt.jama.core.po.projects.Project;
import com.amt.jama.core.po.projects.ProjectDataListWrapper;
import com.amt.jama.core.po.projects.ProjectDataWrapper;
import com.amt.jama.core.po.relationships.Relationship;
import com.amt.jama.core.po.relationships.RelationshipDataListWrapper;
import com.amt.jama.core.po.relationshiptypes.RelationshipType;
import com.amt.jama.core.po.relationshiptypes.RelationshipTypeDataListWrapper;
import com.amt.jama.core.po.releases.Release;
import com.amt.jama.core.po.releases.ReleaseDataListWrapper;
import com.amt.jama.core.po.releases.ReleaseDataWrapper;
import com.amt.jama.core.po.syncstatus.SyncStatus;
import com.amt.jama.core.po.syncstatus.SyncStatusDataWrapper;
import com.amt.jama.core.po.tags.Tag;
import com.amt.jama.core.po.tags.TagDataListWrapper;
import com.amt.jama.core.po.tags.TagDataWrapper;
import com.amt.jama.core.po.testcycles.TestCycle;
import com.amt.jama.core.po.testcycles.TestCycleDataWrapper;
import com.amt.jama.core.po.testcycles.TestCycleTestGroup;
import com.amt.jama.core.po.testcycles.TestCycleTestGroupDataWrapper;
import com.amt.jama.core.po.testgroup.TestGroup;
import com.amt.jama.core.po.testgroup.TestGroupDataListWrapper;
import com.amt.jama.core.po.testgroup.TestGroupDataWrapper;
import com.amt.jama.core.po.testplans.TestPlan;
import com.amt.jama.core.po.testplans.TestPlanDataListWrapper;
import com.amt.jama.core.po.testplans.TestPlanDataWrapper;
import com.amt.jama.core.po.testruns.TestRun;
import com.amt.jama.core.po.testruns.TestRunDataListWrapper;
import com.amt.jama.core.po.testruns.TestRunDataWrapper;
import com.amt.jama.core.po.usergroups.UserGroup;
import com.amt.jama.core.po.usergroups.UserGroupDataListWrapper;
import com.amt.jama.core.po.usergroups.UserGroupDataWrapper;
import com.amt.jama.core.po.users.User;
import com.amt.jama.core.po.users.UserDataListWrapper;
import com.amt.jama.core.po.users.UserDataWrapper;
import com.amt.jama.core.po.version.*;
import com.amt.jama.core.po.workstatus.WorkflowTransition;
import com.amt.jama.core.po.workstatus.WorkflowTransitionDataListWrapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JamaInstance extends JamaUtil {

    /**
     * 用户存储用户名和密码
     */
    private Map<String, String> headers = new ConcurrentHashMap<>();
    /**
     * 查询专用Util
     */
    private HttpPoolUtils poolUtils = null;

    /**
     * 初始化
     *
     * @param baseUrl        跟路径
     * @param username       用户名
     * @param password       密码
     * @param restApiVersion rest api的版本
     * @throws Exception 抛出
     */
    public JamaInstance(String baseUrl, String username, String password, String restApiVersion) throws Exception {
        // set authorization
        headers.put(HttpConstants.AUTHORIZATION, HttpConstants.BASIC + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
        // init http pool
        poolUtils = HttpPoolUtils.getInstance(baseUrl);
        // init jama instance
        setRestVersion(restApiVersion);
    }

    /**
     * 初始化
     *
     * @param headers        headers信息
     * @param poolUtils      查询Util
     * @param restApiVersion rest api的版本
     */
    public JamaInstance(Map<String, String> headers, HttpPoolUtils poolUtils, String restApiVersion) {
        setRestVersion(restApiVersion);
        this.headers = headers;
        this.poolUtils = poolUtils;
    }

    /**
     * 销毁
     *
     * @throws Exception 抛出
     */
    public void destroy() throws Exception {
        poolUtils.destroy();
    }

    /**
     * 初始化缓存信息
     *
     * @throws Exception
     */
    public void initCache() throws Exception {
        new Timer("initCache").schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    getAllProjects();
                } catch (Exception e) {
                    HttpPoolUtils.LOG.info(e.getMessage());
                }
                try {
                    getAllItemTypes();
                } catch (Exception e) {
                    HttpPoolUtils.LOG.info(e.getMessage());
                }
            }
        }, 1000, 1000 * 10);

    }

    public List<Project> getAllProjects() throws Exception {
        List<Project> projects = getProjects(0);
        if (projects != null && projects.size() > 0) {
            int size = projects.size();
            int index = 1;
            while (size >= 20) {
                List<Project> projectList = getProjects(index * 20);
                projects.addAll(projectList);
                size = projectList.size();
                index++;
            }
        }
        return projects;
    }

    public List<Project> getProjects(Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        Thread.sleep(10000);
        ProjectDataListWrapper projects = getProjectClient().getProjects(queryParameters, poolUtils, headers);
        return projects != null ? projects.getData() : null;
    }

    public Project getProject(Integer projectId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("projectId", projectId.toString());
        ProjectDataWrapper project = getProjectClient().getProject(pathParameters, null, poolUtils, headers);
        return project != null ? project.getData() : null;
    }

    public List<ItemType> getAllItemTypes() throws Exception {
        List<ItemType> itemTypes = getItemTypes(0);
        if (itemTypes != null && itemTypes.size() > 0) {
            int size = itemTypes.size();
            int index = 1;
            while (size >= 20) {
                List<ItemType> itemTypeList = getItemTypes(index * 20);
                itemTypes.addAll(itemTypeList);
                size = itemTypeList.size();
                index++;
            }
        }
        return itemTypes;
    }

    public List<ItemType> getItemTypes(Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        ItemTypeDataListWrapper itemTypes = getItemTypeClient().getItemTypes(queryParameters, poolUtils, headers);
        if (itemTypes != null) {
            List<ItemType> data = itemTypes.getData();
            putPickListOptionsIntoItemType(data);
            return data;
        } else {
            return null;
        }
    }

    public ItemType getItemType(Integer itemTypeId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("itemTypeId", itemTypeId.toString());
        ItemTypeDataWrapper itemType = getItemTypeClient().getItemType(pathParameters, null, poolUtils, headers);
        if (itemType != null) {
            List<ItemType> data = Collections.singletonList(itemType.getData());
            putPickListOptionsIntoItemType(data);
            return data.get(0);
        } else {
            return null;
        }
    }

    public ItemType getItemType(String issueTypeName) throws Exception {
        List<ItemType> itemTypes = getItemTypes(0);
        if (itemTypes != null) {
            Optional<ItemType> first = itemTypes.stream()
                    .filter(itemType -> itemType.getTypeKey().equals(issueTypeName)
                            || itemType.getDisplay().equals(issueTypeName)).findFirst();
            if (first.isPresent()) {
                return first.get();
            }
        }
        return null;
    }


    public List<Item> getItems(Integer projectId, Integer startAt, Boolean lazy) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        queryParameters.put("project", projectId.toString());
        ItemDataListWrapper items = getItemClient().getItems(queryParameters, poolUtils, headers);
        if (items != null) {
            List<Item> data = items.getData();
            for (Item d : data) {
                putFieldValueIntoItems(d, lazy);
            }
            return data;
        }
        return null;
    }

    public Item getItem(Integer itemId, Boolean lazy) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        ItemDataWrapper item = getItemClient().getItem(pathParameters, null, poolUtils, headers);
        if (item != null) {
            Item data = item.getData();
            putFieldValueIntoItems(data, lazy);
            return data;
        }
        return null;
    }

    public List<Activity> getActivities(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        ActivityDataListWrapper activities = getItemClient().getActivities(pathParameters, queryParameters, poolUtils, headers);
        return activities != null ? activities.getData() : null;
    }

    public List<Attachment> getAttachments(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        AttachmentDataListWrapper attachments = getItemClient().getAttachments(pathParameters, queryParameters, poolUtils, headers);
        return attachments != null ? attachments.getData() : null;
    }

    public List<Item> getChildren(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        ItemDataListWrapper children = getItemClient().getChildren(pathParameters, queryParameters, poolUtils, headers);
        return children != null ? children.getData() : null;
    }

    public List<Item> getDownstreamRelated(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        ItemDataListWrapper downstreamRelated = getItemClient().getDownstreamRelated(pathParameters, queryParameters, poolUtils, headers);
        return downstreamRelated != null ? downstreamRelated.getData() : null;
    }

    public List<Relationship> getDownstreamRelationships(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        RelationshipDataListWrapper downstreamRelationships = getItemClient().getDownstreamRelationships(pathParameters, queryParameters, poolUtils, headers);
        return downstreamRelationships != null ? downstreamRelationships.getData() : null;
    }

    public List<Comment> getComments(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        CommentDataListWrapper comments = getItemClient().getComments(pathParameters, queryParameters, poolUtils, headers);
        return comments != null ? comments.getData() : null;
    }

    public List<Link> getLinks(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        LinkDataListWrapper links = getItemClient().getLinks(pathParameters, queryParameters, poolUtils, headers);
        return links != null ? links.getData() : null;
    }

    public Link getLink(Integer itemId, Integer linkId) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        pathParameters.put("linkId", linkId.toString());
        LinkDataWrapper link = getItemClient().getLink(pathParameters, queryParameters, poolUtils, headers);
        return link != null ? link.getData() : null;
    }

    public Location getLocation(Integer itemId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        LocationDataWrapper location = getItemClient().getLocation(pathParameters, null, poolUtils, headers);
        return location != null ? location.getData() : null;
    }

    public Lock getLock(Integer itemId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        LockDataWrapper lock = getItemClient().getLock(pathParameters, null, poolUtils, headers);
        return lock != null ? lock.getData() : null;
    }

    public Item getParent(Integer itemId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        ItemDataWrapper parent = getItemClient().getParent(pathParameters, null, poolUtils, headers);
        return parent != null ? parent.getData() : null;
    }

    public List<Item> getSyncedItems(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        ItemDataListWrapper items = getItemClient().getSyncedItems(pathParameters, queryParameters, poolUtils, headers);
        return items != null ? items.getData() : null;
    }

    public SyncStatus getSyncedItemStatus(Integer itemId, Integer syncedItemId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        pathParameters.put("syncedItemId", syncedItemId.toString());
        SyncStatusDataWrapper syncStatus = getItemClient().getSyncStatus(pathParameters, null, poolUtils, headers);
        return syncStatus != null ? syncStatus.getData() : null;
    }

    public Tag getTag(Integer itemId, Integer tagId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        pathParameters.put("tagId", tagId.toString());
        TagDataWrapper tag = getItemClient().getTag(pathParameters, null, poolUtils, headers);
        return tag != null ? tag.getData() : null;
    }

    public List<Tag> getTags(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        TagDataListWrapper tags = getItemClient().getTags(pathParameters, queryParameters, poolUtils, headers);
        return tags != null ? tags.getData() : null;
    }

    public List<Item> getUpstreamRelated(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        ItemDataListWrapper upstreamRelated = getItemClient().getUpstreamRelated(pathParameters, queryParameters, poolUtils, headers);
        return upstreamRelated != null ? upstreamRelated.getData() : null;
    }

    public List<Relationship> getUpstreamRelationships(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        RelationshipDataListWrapper upstreamRelationships = getItemClient().getUpstreamRelationships(pathParameters, queryParameters, poolUtils, headers);
        return upstreamRelationships != null ? upstreamRelationships.getData() : null;
    }

    public List<Version> getVersions(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        VersionDataListWrapper versions = getItemClient().getVersions(pathParameters, queryParameters, poolUtils, headers);
        return versions != null ? versions.getData() : null;
    }

    public Version getVersion(Integer itemId, Integer versionNum) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        pathParameters.put("versionNum", versionNum.toString());
        VersionDataWrapper version = getItemClient().getVersion(pathParameters, null, poolUtils, headers);
        return version != null ? version.getData() : null;
    }

    public VersionedItem getVersionedItem(Integer itemId, Integer versionNum) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", itemId.toString());
        pathParameters.put("versionNum", versionNum.toString());
        VersionedItemDataWrapper versionedItem = getItemClient().getVersionedItem(pathParameters, null, poolUtils, headers);
        return versionedItem != null ? versionedItem.getData() : null;
    }

    public List<WorkflowTransition> getWorkflowTransitions(Integer itemId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        pathParameters.put("id", itemId.toString());
        WorkflowTransitionDataListWrapper workflowTransitionOptions = getItemClient().getWorkflowTransitionOptions(pathParameters, queryParameters, poolUtils, headers);
        return workflowTransitionOptions != null ? workflowTransitionOptions.getData() : null;
    }

    public List<RelationshipType> getRelationshipTypes(Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        RelationshipTypeDataListWrapper relationshipTypes = getRelationshipClient().getRelationshipTypes(queryParameters, poolUtils, headers);
        return relationshipTypes != null ? relationshipTypes.getData() : null;
    }

    public List<Relationship> getRelationships(Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        RelationshipDataListWrapper relationships = getRelationshipClient().getRelationships(queryParameters, poolUtils, headers);
        return relationships != null ? relationships.getData() : null;
    }

    public Release getRelease(Integer releaseId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("releaseId", releaseId.toString());
        ReleaseDataWrapper release = getReleaseClient().getRelease(pathParameters, null, poolUtils, headers);
        return release != null ? release.getData() : null;
    }

    public List<Release> getReleases(Integer projectId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        queryParameters.put("startAt", startAt.toString());
        queryParameters.put("project", projectId.toString());
        ReleaseDataListWrapper releases = getReleaseClient().getReleases(queryParameters, poolUtils, headers);
        return releases != null ? releases.getData() : null;
    }

    public TestCycle getTestCycle(Integer testCycleId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("testCycleId", testCycleId.toString());
        TestCycleDataWrapper testCycle = getTestCycleClient().getTestCycle(pathParameters, null, poolUtils, headers);
        return testCycle != null ? testCycle.getData() : null;
    }

    public TestCycleTestGroup getTestCycleTestGroup(Integer testCycleId, Integer testGroupId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("testCycleId", testCycleId.toString());
        pathParameters.put("testGroupId", testGroupId.toString());
        TestCycleTestGroupDataWrapper testGroup = getTestCycleClient().getTestGroup(pathParameters, null, poolUtils, headers);
        return testGroup != null ? testGroup.getData() : null;
    }

    public List<TestRun> getTestRuns(Integer testCycleId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = new ConcurrentHashMap<>();
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("testCycleId", testCycleId.toString());
        queryParameters.put("startAt", startAt.toString());
        TestRunDataListWrapper testRuns = getTestCycleClient().getTestRuns(pathParameters, queryParameters, poolUtils, headers);
        return testRuns != null ? testRuns.getData() : null;
    }

    public Version getTestCycleVersion(Integer testCycleId, Integer versionNum) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("testCycleId", testCycleId.toString());
        pathParameters.put("versionNum", versionNum.toString());
        VersionDataWrapper version = getTestCycleClient().getVersion(pathParameters, null, poolUtils, headers);
        return version != null ? version.getData() : null;
    }

    public VersionedTestCycle getTestCycleVersionedItem(Integer testCycleId, Integer versionNum) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("testCycleId", testCycleId.toString());
        pathParameters.put("versionNum", versionNum.toString());
        VersionedTestCycleDataWrapper versionedItem = getTestCycleClient().getVersionedItem(pathParameters, null, poolUtils, headers);
        return versionedItem != null ? versionedItem.getData() : null;
    }

    public List<Version> getTestCycleVersions(Integer testCycleId, Integer startAt) throws Exception {
        Map<String, String> queryParameter = putValues("startAt", startAt);
        Map<String, String> pathParameters = putValues("testCycleId", testCycleId);
        VersionDataListWrapper versions = getTestCycleClient().getVersions(pathParameters, queryParameter, poolUtils, headers);
        return versions != null ? versions.getData() : null;
    }

    public TestGroup getTestGroup(Integer testPlanId, Integer testGroupId) throws Exception {
        Map<String, String> pathParameters = new ConcurrentHashMap<>();
        pathParameters.put("id", testPlanId.toString());
        pathParameters.put("testGroupId", testGroupId.toString());
        TestGroupDataWrapper testGroup = getTestPlanClient().getTestGroup(pathParameters, null, poolUtils, headers);
        return testGroup != null ? testGroup.getData() : null;
    }

    public List<TestGroup> getTestGroups(Integer testPlanId, Integer startAt) throws Exception {
        Map<String, String> pathParameters = putValues("id", testPlanId);
        Map<String, String> queryParameters = putValues("startAt", startAt);
        TestGroupDataListWrapper testGroups = getTestPlanClient().getTestGroups(pathParameters, queryParameters, poolUtils, headers);
        return testGroups != null ? testGroups.getData() : null;
    }

    public TestPlan getTestPlan(Integer testPlanId) throws Exception {
        Map<String, String> pathParameters = putValues("id", testPlanId);
        TestPlanDataWrapper testPlan = getTestPlanClient().getTestPlan(pathParameters, null, poolUtils, headers);
        return testPlan != null ? testPlan.getData() : null;
    }

    public List<TestPlan> getTestPlans(Integer projectId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = putValues("project", projectId, "startAt", startAt);
        TestPlanDataListWrapper testPlans = getTestPlanClient().getTestPlans(queryParameters, poolUtils, headers);
        return testPlans != null ? testPlans.getData() : null;
    }

    public TestRun getTestRun(Integer testRunId) throws Exception {
        Map<String, String> pathParameters = putValues("id", testRunId);
        TestRunDataWrapper testRun = getTestRunClient().getTestRun(pathParameters, null, poolUtils, headers);
        return testRun != null ? testRun.getData() : null;
    }

    public List<TestRun> getTestRuns(Integer testCycleId, Integer testCaseId, Integer testPlanId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = putValues("testCycle", testCycleId, "testCase", testCaseId, "testPlan", testPlanId, "startAt", startAt);
        TestRunDataListWrapper testRuns = getTestRunClient().getTestRuns(queryParameters, poolUtils, headers);
        return testRuns != null ? testRuns.getData() : null;
    }

    public List<UserGroup> getUserGroups(Integer projectId, Integer startAt) throws Exception {
        Map<String, String> queryParameters = putValues("projectId", projectId, "startAt", startAt);
        UserGroupDataListWrapper userGroups = getUserGroupClient().getUserGroups(queryParameters, poolUtils, headers);
        return userGroups != null ? userGroups.getData() : null;
    }

    public UserGroup getUserGroup(Integer userGroupId) throws Exception {
        Map<String, String> pathParameters = putValues("id", userGroupId);
        UserGroupDataWrapper userGroup = getUserGroupClient().getUserGroup(pathParameters, null, poolUtils, headers);
        return userGroup != null ? userGroup.getData() : null;
    }

    public List<User> getUsers(Integer userGroupId, Integer startAt) throws Exception {
        Map<String, String> pathParameters = putValues("id", userGroupId);
        Map<String, String> queryParameters = putValues("startAt", startAt);
        UserDataListWrapper users = getUserGroupClient().getUsers(pathParameters, queryParameters, poolUtils, headers);
        return users != null ? users.getData() : null;
    }

    public List<Filter> getFavoriteFilters(Integer startAt, Map<String, String> headers) throws Exception {
        Map<String, String> queryParameters = putValues("startAt", startAt);
        FilterDataListWrapper favoriteFilters = getUserClient().getFavoriteFilters(queryParameters, poolUtils, headers);
        return favoriteFilters != null ? favoriteFilters.getData() : null;
    }

    public User getCurrentUser(Map<String, String> headers) throws Exception {
        UserDataWrapper currentUser = getUserClient().getCurrentUser(null, poolUtils, headers);
        return currentUser != null ? currentUser.getData() : null;
    }

    public User getUser(Integer userId) throws Exception {
        Map<String, String> pathParameters = putValues("userId", userId);
        UserDataWrapper user = getUserClient().getUser(pathParameters, null, poolUtils, headers);
        return user != null ? user.getData() : null;
    }

    public List<User> getUsers(String username, String email, String firstName, String lastName, String licenseType, Boolean includeInactive, Integer startAt) throws Exception {
        Map<String, String> queryParameters = putValues("username", username, "email", email, "firstName", firstName, "firstName", firstName, "licenseType", licenseType, "includeInactive", includeInactive, "startAt", startAt);
        UserDataListWrapper users = getUserClient().getUsers(queryParameters, poolUtils, headers);
        return users != null ? users.getData() : null;
    }


    public List<PickList> getPickLists(Integer startAt) throws Exception {
        Map<String, String> queryParameters = putValues("startAt", startAt);
        PickListDataListWrapper pickLists = getPickListClient().getPickLists(queryParameters, poolUtils, headers);
        putOptionIntoPickList(pickLists.getData());
        return pickLists.getData();
    }

    public PickList getPickList(Integer pickListId) throws Exception {
        Map<String, String> pathParameters = putValues("picklistId", pickListId);
        PickListDataWrapper pickList = getPickListClient().getPickList(pathParameters, null, poolUtils, headers);
        putOptionIntoPickList(Collections.singletonList(pickList.getData()));
        return pickList.getData();
    }


    public List<PickListOption> getPickListOptions(Integer pickListId, Integer startAt) throws Exception {
        Map<String, String> pathParameters = putValues("picklistId", pickListId);
        Map<String, String> queryParameters = putValues("startAt", startAt);
        PickListOptionDataListWrapper pickListOptions = getPickListClient().getPickListOptions(pathParameters, queryParameters, poolUtils, headers);
        return pickListOptions != null ? pickListOptions.getData() : null;
    }


    public PickListOption getPickListOption(Integer pickListOptionId) throws Exception {
        Map<String, String> pathParameters = putValues("picklistOptionId", pickListOptionId);
        PickListOptionDataWrapper pickListOption = getPickListClient().getPickListOption(pathParameters, null, poolUtils, headers);
        return pickListOption != null ? pickListOption.getData() : null;
    }

    private void putOptionIntoPickList(List<PickList> pickLists) throws Exception {
        if (pickLists != null) {
            for (PickList pickList : pickLists) {
                Integer id = pickList.getId();
                List<PickListOption> optionList = new ArrayList<>();
                List<PickListOption> pickListOptions = getPickListOptions(id, 0);
                if (pickListOptions != null) {
                    int size = pickListOptions.size();
                    optionList.addAll(pickListOptions);
                    int index = 1;
                    while (size >= 20) {
                        pickListOptions = getPickListOptions(id, index * 20);
                        if (pickListOptions != null) {
                            size = pickListOptions.size();
                            optionList.addAll(pickListOptions);
                        } else {
                            break;
                        }
                        index++;
                    }
                }
                pickList.setOptions(optionList);
            }
        }
    }

    private Map<String, String> putValues(Object... values) {
        Map<String, String> map = new ConcurrentHashMap<>();
        if (values != null && values.length > 0) {
            int length = values.length % 2 == 0 ? values.length / 2 : (values.length - 1) / 2;
            for (int i = 0; i < length; i++) {
                String name = values[2 * i].toString();
                Object value = values[2 * i + 1];
                if (value != null) {
                    map.put(name, value.toString());
                }
            }
        }
        return map;
    }


    private void putPickListOptionsIntoItemType(List<ItemType> itemTypes) throws Exception {
        for (ItemType itemType : itemTypes) {
            List<ItemTypeField> fields = itemType.getFields();
            for (ItemTypeField field : fields) {
                Integer pickListId = field.getPickList();
                if (pickListId != null && pickListId > 0) {
                    PickList pickList = getPickList(pickListId);
                    List<PickListOption> options = pickList.getOptions();
                    field.setPickListOptions(options);
                }
            }
        }
    }

    private void putFieldValueIntoItems(AbstractItem item, Boolean lazy) throws Exception {
        if (lazy == null || lazy) {
            return;
        }
        // 1. deal user
        item.setCreatedUser(getUser(item.getCreatedBy()));
        item.setModifiedUser(getUser(item.getModifiedBy()));
        // 2. deal fields
        Map<String, Object> fields = item.getFields();
        Integer itemTypeId = item.getItemType();
        ItemType itemType = getItemType(itemTypeId);
        List<ItemTypeField> itemTypeFields = itemType.getFields();
        List<JamaFieldValue> fieldValues = item.getFieldValues();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            Optional<ItemTypeField> first = itemTypeFields.stream().filter(field -> field.getName().equals(fieldName)).findFirst();
            if (first.isPresent()) {
                ItemTypeField field = first.get();
                String fieldType = field.getFieldType();
                String textType = field.getTextType();
                JamaField jamaField = null;
                JamaFieldValue jamaFieldValue = null;
                //STRING,TEXT,USER,RELEASE,URL_STRING,LOOKUP,CALCULATED,DATE,BOOLEAN,INTEGER,MULTI_LOOKUP,DOCUMENT_TYPE_ITEM_LOOKUP,ROLLUP
                switch (fieldType) {
                    case "BOOLEAN":
                        jamaField = new FlagField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new FlagFieldValue();
                        jamaFieldValue.setValue(value);
                        break;
                    case "CALCULATED":
                        jamaField = new CalculatedField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new CalculatedFieldValue();
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "DATE":
                        jamaField = new DateField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new DateFieldValue();
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "DOCUMENT_TYPE_ITEM_LOOKUP":
                    case "URL_STRING":
                    case "STRING":
                        // like String
                        jamaField = new TextField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new TextFieldValue();
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "LOOKUP":
                        jamaField = new PickListField(fieldType, field.getPickList(), field.getPickListOptions());
                        transformTo(field, jamaField);
                        jamaFieldValue = new PickListFieldValue(this);
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "INTEGER":
                        jamaField = new IntegerField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new IntegerFieldValue();
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "MULTI_LOOKUP":
                        jamaField = new MultiSelectField(fieldType, field.getPickList(), field.getPickListOptions());
                        transformTo(field, jamaField);
                        jamaFieldValue = new MultiSelectFieldValue(this);
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "ROLLUP":
                        jamaField = new RollupField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new RollupFieldValue();
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "RELEASE":
                        jamaField = new ReleaseField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new ReleaseFieldValue(this);
                        jamaFieldValue.setValue(value.toString());
                        break;
                    case "TEXT":
                        switch (textType) {
                            case "TEXTAREA":
                                jamaField = new TextBoxField(fieldType);
                                transformTo(field, jamaField);
                                jamaFieldValue = new TextBoxFieldValue();
                                jamaFieldValue.setValue(value.toString());
                                break;
                            case "RICHTEXT":
                                jamaField = new RichTextField(fieldType);
                                transformTo(field, jamaField);
                                jamaFieldValue = new RichTextFieldValue();
                                jamaFieldValue.setValue(value.toString());
                                break;
                        }
                        break;
                    case "USER":
                        jamaField = new UserField(fieldType);
                        transformTo(field, jamaField);
                        jamaFieldValue = new UserFieldValue(this);
                        jamaFieldValue.setValue(value.toString());
                        break;
                }
                if (jamaFieldValue != null) {
                    jamaFieldValue.setField(jamaField);
                    jamaFieldValue.setJamaInstance(this);
                }
                fieldValues.add(jamaFieldValue);

            }
        }
    }


    private void transformTo(ItemTypeField itemTypeField, JamaField jamaField) {
        jamaField.setRequired(itemTypeField.getRequired());
        jamaField.setReadOnly(itemTypeField.getReadOnly());
        jamaField.setLabel(itemTypeField.getLabel());
        jamaField.setName(itemTypeField.getName());
        jamaField.setSynchronize(itemTypeField.getSynchronize());
        jamaField.setId(itemTypeField.getId());
        jamaField.setTriggerSuspect(itemTypeField.getTriggerSuspect());
        jamaField.setJamaInstance(this);
    }

}
