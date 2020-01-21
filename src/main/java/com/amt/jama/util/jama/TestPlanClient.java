package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractItemDataListWrapper;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.activities.ActivityDataListWrapper;
import com.amt.jama.core.po.attachments.AttachmentDataListWrapper;
import com.amt.jama.core.po.comments.CommentDataListWrapper;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.items.ItemDataListWrapper;
import com.amt.jama.core.po.items.ItemDataWrapper;
import com.amt.jama.core.po.link.LinkDataListWrapper;
import com.amt.jama.core.po.link.LinkDataWrapper;
import com.amt.jama.core.po.lock.LockDataWrapper;
import com.amt.jama.core.po.relationships.RelationshipDataListWrapper;
import com.amt.jama.core.po.request.*;
import com.amt.jama.core.po.tags.TagDataListWrapper;
import com.amt.jama.core.po.tags.TagDataWrapper;
import com.amt.jama.core.po.testcycles.TestCycleDataListWrapper;
import com.amt.jama.core.po.testgroup.TestGroupDataListWrapper;
import com.amt.jama.core.po.testgroup.TestGroupDataWrapper;
import com.amt.jama.core.po.testplans.TestPlanDataListWrapper;
import com.amt.jama.core.po.testplans.TestPlanDataWrapper;
import com.amt.jama.core.po.version.VersionDataListWrapper;
import com.amt.jama.core.po.version.VersionDataWrapper;
import com.amt.jama.core.po.version.VersionedTestPlanDataWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wikle.wang
 */
public class TestPlanClient extends BaseClient {

    TestPlanClient(String restVersion) {
        super(restVersion);
    }

    /**
     * POST: Add an existing attachment to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemAttachment
     */
    CreatedResponse addAttachment(RequestItemAttachment itemAttachment, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_PLANS_ID_ATTACHMENTS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(itemAttachment), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new link for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLink
     */
    CreatedResponse addLink(RequestLink link, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_PLANS_ID_LINKS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(link), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Add an existing tag to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemTag
     */

    CreatedResponse addTag(RequestItemTag itemTag, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_PLANS_ID_TAGS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(itemTag), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Add an existing tag to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestGroup
     */

    CreatedResponse addTestGroup(RequestTestGroup testGroup, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_PLANS_ID_TEST_GROUPS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(testGroup), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new test cycle
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestCycle
     */
    CreatedResponse createTestCycle(RequestTestCycle testCycle, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_PLANS_ID_TEST_CYCLES_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(testCycle), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new test plan
     * Body: RequestTestPlan
     */
    CreatedResponse addTestPlan(RequestTestCycle testCycle, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_PLANS_POST, null, FastJsonUtils.toJSONNoFeatures(testCycle), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Delete the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     */
    AbstractRestResponse removeLink(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_PLANS_ID_LINKS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing test case from the test group
     * Path parameters:
     * 1. id (required)
     * 2. testGroupId (required)
     * 2. testCaseId (required)
     */
    AbstractRestResponse removeTestCase(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_PLANS_ID_TEST_GROUP_ID_TEST_CASE_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. testGroupId (required)
     */
    AbstractRestResponse removeTestGroup(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_PLANS_ID_TEST_GROUP_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    AbstractRestResponse deleteTestPlan(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_PLANS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get all activities for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    ActivityDataListWrapper getActivities(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ActivityDataListWrapper) get(HttpUrls.TEST_PLANS_ID_ACTIVITIES, pathParameters, queryParameters, ActivityDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all attachments for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    AttachmentDataListWrapper getAttachments(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AttachmentDataListWrapper) get(HttpUrls.TEST_PLANS_ID_ATTACHMENTS, pathParameters, queryParameters, AttachmentDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all downstream related items for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    AbstractItemDataListWrapper getDownstreamRelated(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractItemDataListWrapper) get(HttpUrls.TEST_PLANS_ID_DOWN_STREAM_RELATED, pathParameters, queryParameters, AbstractItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all downstream related items for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    RelationshipDataListWrapper getDownstreamRelationships(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipDataListWrapper) get(HttpUrls.TEST_PLANS_ID_DOWN_STREAM_RELATIONSHIPS, pathParameters, queryParameters, RelationshipDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all comments for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    CommentDataListWrapper getComments(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CommentDataListWrapper) get(HttpUrls.TEST_PLANS_ID_COMMENTS, pathParameters, queryParameters, AbstractItemDataListWrapper.class, poolUtils, headers);
    }


    /**
     * GET: Get all comments for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    LinkDataWrapper getLink(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LinkDataWrapper) get(HttpUrls.TEST_PLANS_ID_LINKS_ID, pathParameters, queryParameters, LinkDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all comments for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    LinkDataListWrapper getLinks(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LinkDataListWrapper) get(HttpUrls.TEST_PLANS_ID_LINKS, pathParameters, queryParameters, LinkDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the locked state, last locked date, and last locked by user for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    LockDataWrapper getLock(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LockDataWrapper) get(HttpUrls.TEST_PLANS_ID_LOCK, pathParameters, queryParameters, LockDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. tagId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TagDataWrapper getTag(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TagDataWrapper) get(HttpUrls.TEST_PLANS_ID_TAGS_ID, pathParameters, queryParameters, TagDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all tags for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    TagDataListWrapper getTags(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TagDataListWrapper) get(HttpUrls.TEST_PLANS_ID_TAGS, pathParameters, queryParameters, TagDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the test case with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testCaseId (required)
     * 1. testGroupId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    ItemDataWrapper getTestCase(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataWrapper) get(HttpUrls.TEST_PLANS_ID_TEST_GROUPS_ID_TEST_CASES_ID, pathParameters, queryParameters, ItemDataWrapper.class, poolUtils, headers);
    }


    /**
     * GET: Get all test cases associated with the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testGroupId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    ItemDataListWrapper getTestCases(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.TEST_PLANS_ID_TEST_GROUPS_ID_TEST_CASES, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all test cycles for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    TestCycleDataListWrapper getTestCycles(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestCycleDataListWrapper) get(HttpUrls.TEST_PLANS_ID_TEST_CYCLES, pathParameters, queryParameters, TestCycleDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testGroupId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TestGroupDataWrapper getTestGroup(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestGroupDataWrapper) get(HttpUrls.TEST_PLANS_ID_TEST_GROUPS_ID, pathParameters, queryParameters, TestGroupDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all test groups for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    TestGroupDataListWrapper getTestGroups(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestGroupDataListWrapper) get(HttpUrls.TEST_PLANS_ID_TEST_GROUPS, pathParameters, queryParameters, TestGroupDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TestPlanDataWrapper getTestPlan(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestPlanDataWrapper) get(HttpUrls.TEST_PLANS_ID, pathParameters, queryParameters, TestPlanDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all test plans in the project with the specified ID
     * Query parameters:
     * 1. project (required)
     * 2. startAt (optional)
     * 3. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    TestPlanDataListWrapper getTestPlans(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestPlanDataListWrapper) get(HttpUrls.TEST_PLANS, null, queryParameters, TestPlanDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all upstream related items for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    AbstractItemDataListWrapper getUpstreamRelated(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractItemDataListWrapper) get(HttpUrls.TEST_PLANS_ID_UPSTREAM_RELATED, pathParameters, queryParameters, AbstractItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all upstream relationships for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    RelationshipDataListWrapper getUpstreamRelationships(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipDataListWrapper) get(HttpUrls.TEST_PLANS_ID_UPSTREAM_RELATIONSHIPS, pathParameters, queryParameters, RelationshipDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionDataWrapper getVersion(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataWrapper) get(HttpUrls.TEST_PLANS_ID_VERSIONS_NUM, pathParameters, queryParameters, VersionDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the snapshot of the test plan at the specified version
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionedTestPlanDataWrapper getVersionItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionedTestPlanDataWrapper) get(HttpUrls.TEST_PLANS_ID_VERSIONS_NUM_ITEM, pathParameters, queryParameters, VersionedTestPlanDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all versions for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionDataListWrapper getVersions(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataListWrapper) get(HttpUrls.TEST_PLANS_ID_VERSIONS, pathParameters, queryParameters, VersionDataListWrapper.class, poolUtils, headers);
    }

    /**
     * PATCH: Update the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: array[RequestPatchOperation]
     */
    AbstractRestResponse patchTestPlan(List<RequestPatchOperation> operations, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) patch(HttpUrls.TEST_PLANS_ID_PATCH, pathParameters, FastJsonUtils.toJSONNoFeatures(operations), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * POST: Add an existing test case to the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testGroupId (required)
     * Body: RequestTestGroupTestCase
     */
    CreatedResponse addTestCase(List<RequestPatchOperation> operations, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) patch(HttpUrls.TEST_PLANS_ID_TEST_GROUPS_ID_TEST_CASE_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(operations), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing attachment from the item
     * Path parameters:
     * 1. id (required)
     * 1. attachmentId (required)
     */
    AbstractRestResponse deleteAttachment(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_PLANS_ID_ATTACHMENTS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing tag from the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. tagId (required)
     */
    AbstractRestResponse deleteTag(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_PLANS_ID_TAGS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the archived status of the test plan
     * Path parameters:
     * 1. id (required)
     * Body: RequestArchivedStatus
     */
    AbstractRestResponse updateArchived(RequestArchivedStatus archivedStatus, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_PLANS_ID_ARCHIVED_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(archivedStatus), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the archived status of the test plan
     * Path parameters:
     * 1. id (required)
     * 1. linkId (required)
     * Body: RequestLink
     */
    AbstractRestResponse updateLink(RequestLink link, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_PLANS_ID_LINKS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(link), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLink
     */
    AbstractRestResponse updateLock(RequestLock lock, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_PLANS_ID_LOCK_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(lock), null, AbstractRestResponse.class, poolUtils, headers);
    }


    /**
     * PUT: Update the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testGroup (required)
     * Body: RequestTestGroup
     */
    AbstractRestResponse updateTestGroup(RequestTestGroup testGroup, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_PLANS_ID_TEST_GROUPS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(testGroup), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestPlan
     */
    AbstractRestResponse updateTestPlan(RequestTestPlan testPlan, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_PLANS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(testPlan), null, AbstractRestResponse.class, poolUtils, headers);
    }

}
