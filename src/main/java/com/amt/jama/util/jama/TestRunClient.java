package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractItemDataListWrapper;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.activities.ActivityDataListWrapper;
import com.amt.jama.core.po.attachments.AttachmentDataListWrapper;
import com.amt.jama.core.po.comments.CommentDataListWrapper;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.link.LinkDataListWrapper;
import com.amt.jama.core.po.link.LinkDataWrapper;
import com.amt.jama.core.po.lock.LockDataWrapper;
import com.amt.jama.core.po.relationships.RelationshipDataListWrapper;
import com.amt.jama.core.po.relationshiptypes.RelationshipTypeDataListWrapper;
import com.amt.jama.core.po.request.*;
import com.amt.jama.core.po.tags.TagDataListWrapper;
import com.amt.jama.core.po.tags.TagDataWrapper;
import com.amt.jama.core.po.testruns.TestRunDataListWrapper;
import com.amt.jama.core.po.testruns.TestRunDataWrapper;
import com.amt.jama.core.po.version.VersionDataListWrapper;
import com.amt.jama.core.po.version.VersionDataWrapper;
import com.amt.jama.core.po.version.VersionedTestRunDataWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wikle.wang
 */
public class TestRunClient extends BaseClient {

    TestRunClient(String restVersion) {
        super(restVersion);
    }

    /**
     * POST: Add an existing attachment to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemAttachment
     */
    CreatedResponse addAttachment(RequestItemAttachment itemAttachment, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_RUNS_ID_ATTACHMENTS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(itemAttachment), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new link for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLink
     */
    CreatedResponse addLink(RequestLink link, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_RUNS_ID_LINKS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(link), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Add an existing tag to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemTag
     */
    CreatedResponse addTag(RequestTag tag, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_RUNS_ID_TAGS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(tag), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     */
    AbstractRestResponse deleteLink(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_RUNS_ID_LINKS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    AbstractRestResponse deleteTestRun(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_RUNS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get all activities for the test run with the specified ID
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
        return (ActivityDataListWrapper) get(HttpUrls.TEST_RUNS_ID_ACTIVITIES, pathParameters, queryParameters, ActivityDataListWrapper.class, poolUtils, headers);
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
        return (AttachmentDataListWrapper) get(HttpUrls.TEST_RUNS_ID_ATTACHMENTS, pathParameters, queryParameters, AttachmentDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all downstream related items for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    AbstractItemDataListWrapper getDownStreamRelated(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractItemDataListWrapper) get(HttpUrls.TEST_RUNS_ID_DOWNSTREAM_RELATED, pathParameters, queryParameters, AbstractItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all downstream relationships for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    RelationshipDataListWrapper getDownStreamRelations(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipDataListWrapper) get(HttpUrls.TEST_RUNS_ID_DOWNSTREAM_RELATIONSHIPS, pathParameters, queryParameters, RelationshipDataListWrapper.class, poolUtils, headers);
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
        return (CommentDataListWrapper) get(HttpUrls.TEST_RUNS_ID_COMMENTS, pathParameters, queryParameters, CommentDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all links for the item with the specified ID
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
        return (LinkDataListWrapper) get(HttpUrls.TEST_RUNS_ID_LINKS, pathParameters, queryParameters, LinkDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    LinkDataWrapper getLink(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LinkDataWrapper) get(HttpUrls.TEST_RUNS_ID_LINKS_ID, pathParameters, queryParameters, LinkDataWrapper.class, poolUtils, headers);
    }

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLock
     */
    LockDataWrapper getLock(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LockDataWrapper) get(HttpUrls.TEST_RUNS_ID_LOCK, pathParameters, queryParameters, LockDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. tagId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TagDataWrapper getTag(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TagDataWrapper) get(HttpUrls.TEST_RUNS_ID_TAGS_ID, pathParameters, queryParameters, TagDataWrapper.class, poolUtils, headers);
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
        return (TagDataListWrapper) get(HttpUrls.TEST_RUNS_ID_TAGS, pathParameters, queryParameters, TagDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TestRunDataWrapper getTestRun(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestRunDataWrapper) get(HttpUrls.TEST_RUNS_ID, pathParameters, queryParameters, TestRunDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Search for test runs associated with test cases, test plans, an test cycles
     * Query parameters:
     * 1. testCycle (optional)
     * Filter by test runs in test cycle id
     * 2. testCase (optional)
     * Filter by test runs from test case id
     * 3. testPlan (optional)
     * Filter by test runs in test plan id
     * 4. sortBy (optional)
     * Sort orders can be added with the name of the field by which to sort, followed by .asc or .desc (e.g. 'name.asc' or 'testCycle.desc'). If not set, this defaults to documentKey.asc.
     * 5. startAt (optional)
     * 6. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 7. include (optional)
     * Links to include as full objects in the linked map
     */
    TestRunDataListWrapper getTestRuns(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestRunDataListWrapper) get(HttpUrls.TEST_RUNS, null, queryParameters, TestRunDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all upstream related items for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    AbstractItemDataListWrapper getUpStreamRelated(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractItemDataListWrapper) get(HttpUrls.TEST_RUNS_ID_UPSTREAM_RELATED, pathParameters, queryParameters, AbstractItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all downstream relationships for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    RelationshipTypeDataListWrapper getUpStreamRelationships(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipTypeDataListWrapper) get(HttpUrls.TEST_RUNS_ID_UPSTREAM_RELATIONSHIPS, pathParameters, queryParameters, RelationshipTypeDataListWrapper.class, poolUtils, headers);
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
        return (VersionDataWrapper) get(HttpUrls.TEST_RUNS_ID_VERSIONS_NUM, pathParameters, queryParameters, VersionDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the snapshot of the test run at the specified version
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionedTestRunDataWrapper getVersionedItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionedTestRunDataWrapper) get(HttpUrls.TEST_RUNS_ID_VERSIONS_NUM_ITEM, pathParameters, queryParameters, VersionedTestRunDataWrapper.class, poolUtils, headers);
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
        return (VersionDataListWrapper) get(HttpUrls.TEST_RUNS_ID_VERSIONS, pathParameters, queryParameters, VersionDataListWrapper.class, poolUtils, headers);
    }

    /**
     * POST: Delete the current test run and then create a new test run based on the latest test case version.
     * Path parameters:
     * 1. id (required)
     */
    CreatedResponse updatedTestCase(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TEST_RUNS_ID_UPDATED_TEST_CASE_POST, pathParameters, null, null, VersionDataListWrapper.class, poolUtils, headers);
    }

    /**
     * PATCH: Update the execution results for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: array[RequestPatchOperation]
     */
    AbstractRestResponse patchTestRun(List<RequestPatchOperation> patchOperations, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) patch(HttpUrls.TEST_RUNS_ID_PATCH, pathParameters, FastJsonUtils.toJSONNoFeatures(patchOperations), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing attachment from the item
     * Path parameters:
     * 1. id (required)
     * 2. attachmentId (required)
     */
    AbstractRestResponse deleteAttachment(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_RUNS_ID_ATTACHMENTS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing tag from the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. tagId (required)
     */
    AbstractRestResponse deleteTag(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_RUNS_ID_TAGS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Body: RequestLink
     */
    AbstractRestResponse updateLink(RequestLink link, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_RUNS_ID_LINKS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(link), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLock
     */
    AbstractRestResponse updateLock(RequestLock lock, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_RUNS_ID_LOCK_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(lock), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the execution results for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestRun
     */
    AbstractRestResponse updateTestRun(RequestTestRun testRun, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_RUNS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(testRun), null, AbstractRestResponse.class, poolUtils, headers);
    }

}