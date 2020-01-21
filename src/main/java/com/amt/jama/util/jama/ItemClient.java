package com.amt.jama.util.jama;

import com.alibaba.fastjson.JSONObject;
import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.activities.ActivityDataListWrapper;
import com.amt.jama.core.po.attachments.AttachmentDataListWrapper;
import com.amt.jama.core.po.comments.CommentDataListWrapper;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.duplicate.DuplicateConfig;
import com.amt.jama.core.po.items.ItemDataListWrapper;
import com.amt.jama.core.po.items.ItemDataWrapper;
import com.amt.jama.core.po.link.LinkDataListWrapper;
import com.amt.jama.core.po.link.LinkDataWrapper;
import com.amt.jama.core.po.location.LocationDataWrapper;
import com.amt.jama.core.po.lock.LockDataWrapper;
import com.amt.jama.core.po.relationships.RelationshipDataListWrapper;
import com.amt.jama.core.po.request.*;
import com.amt.jama.core.po.syncstatus.SyncStatusDataWrapper;
import com.amt.jama.core.po.tags.TagDataListWrapper;
import com.amt.jama.core.po.tags.TagDataWrapper;
import com.amt.jama.core.po.version.VersionDataListWrapper;
import com.amt.jama.core.po.version.VersionDataWrapper;
import com.amt.jama.core.po.version.VersionedItemDataWrapper;
import com.amt.jama.core.po.workstatus.WorkflowTransitionDataListWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wikle.wang
 */
public class ItemClient extends BaseClient {

    public ItemClient(String restVersion) {
        super(restVersion);
    }


    /**
     * POST: Add an existing attachment to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemAttachment
     *
     * @return CreatedResponse
     */
    CreatedResponse addAttachment(RequestItemAttachment itemAttachment, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.ITEMS_ID_ATTACHMENTS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(itemAttachment), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new item
     * Query parameters:
     * 1. setGlobalIdManually (optional)
     * This value must be set to true if you attempt to manually set the Global ID field of an item
     * Body: RequestItem
     *
     * @return CreatedResponse
     */
    public JSONObject createItem(RequestItem item, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (JSONObject) post(HttpUrls.ITEMS_POST, null, FastJsonUtils.toJSONNoFeatures(item), null, JSONObject.class, poolUtils, headers);
    }

    /**
     * POST: Create a new link for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: ResponseLink
     *
     * @return CreatedResponse
     */
    CreatedResponse addLink(RequestLink itemLink, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.ITEMS_ID_LINKS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(itemLink), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Add an existing tag to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemTag
     *
     * @return CreatedResponse
     */
    CreatedResponse addTag(RequestItemTag itemTag, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.ITEMS_ID_TAGS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(itemTag), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing item from the Global ID pool of the item with the specified ID (break sync)
     * Path parameters:
     * 1. id (required)
     * 2. syncedItemId (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse breakSyncOnItem(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.ITEMS_ID_SYNCED_ITEMS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the item with the specified ID (item becomes inactive and can be un-deleted if necessary)
     * Path parameters:
     * 1. id (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse deleteItem(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.ITEMS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a duplicate of item
     * Path parameters:
     * 1. id (required)
     * Body: DuplicateConfig
     *
     * @return CreatedResponse
     */
    CreatedResponse duplicateItem(DuplicateConfig duplicateConfig, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.ITEMS_ID_DUPLICATE_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(duplicateConfig), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Add an existing item to the Global ID pool of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemSyncedItem
     *
     * @return CreatedResponse
     */
    CreatedResponse connectItemToGlobalIDPool(RequestItemSyncedItem syncedItem, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.ITEMS_ID_SYNCED_ITEMS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(syncedItem), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Executes a workflow transition for the item with the specified ID. Valid transitions can be found at /items/{id}/workflowtransitionoptions
     * Path parameters:
     * 1. id (required)
     * Body: RequestTransition
     *
     * @return CreatedResponse
     */
    CreatedResponse executeTransition(RequestTransition transition, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.ITEMS_ID_WORKFLOW_TRANSITIONS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(transition), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get all activities for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ActivityDataListWrapper
     */
    ActivityDataListWrapper getActivities(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ActivityDataListWrapper) get(HttpUrls.ITEMS_ID_ACTIVITIES, pathParameters, queryParameters, ActivityDataListWrapper.class, poolUtils, headers);
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
     *
     * @return AttachmentDataListWrapper
     */
    AttachmentDataListWrapper getAttachments(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AttachmentDataListWrapper) get(HttpUrls.ITEMS_ID_ATTACHMENTS, pathParameters, queryParameters, AttachmentDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all children items for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataListWrapper
     */
    ItemDataListWrapper getChildren(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.ITEMS_ID_CHILDREN, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all downstream related items for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataListWrapper
     */
    ItemDataListWrapper getDownstreamRelated(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.ITEMS_ID_DOWNSTREAM_RELATED, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all downstream relationships for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return RelationshipDataListWrapper
     */
    RelationshipDataListWrapper getDownstreamRelationships(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipDataListWrapper) get(HttpUrls.ITEMS_ID_DOWNSTREAM_RELATIONSHIPS, pathParameters, queryParameters, RelationshipDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataWrapper
     */
    ItemDataWrapper getItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataWrapper) get(HttpUrls.ITEMS_ID, pathParameters, queryParameters, ItemDataWrapper.class, poolUtils, headers);
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
     *
     * @return CommentDataListWrapper
     */
    CommentDataListWrapper getComments(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CommentDataListWrapper) get(HttpUrls.ITEMS_ID_COMMENTS, pathParameters, queryParameters, CommentDataListWrapper.class, poolUtils, headers);
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
     *
     * @return LinkDataListWrapper
     */
    LinkDataListWrapper getLinks(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LinkDataListWrapper) get(HttpUrls.ITEMS_ID_LINKS, pathParameters, queryParameters, LinkDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all items in the project with the specified ID
     * Query parameters:
     * 1. project (required)
     * 2. rootOnly (optional)
     * Set this to true to only get root-level nodes from the item tree
     * 3. startAt (optional)
     * 4. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 5. include (optional)
     * Links to include as full objects in the linked map
     */
    ItemDataListWrapper getItems(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.ITEMS, null, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return LinkDataWrapper
     */
    LinkDataWrapper getLink(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LinkDataWrapper) get(HttpUrls.ITEMS_ID_LINKS_ID, pathParameters, queryParameters, LinkDataWrapper.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     */
    AbstractRestResponse deleteLink(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.ITEMS_ID_LINKS_ID_DELETE, pathParameters, LinkDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get location for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return LocationDataWrapper
     */
    LocationDataWrapper getLocation(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LocationDataWrapper) get(HttpUrls.ITEMS_ID_LOCATION, pathParameters, queryParameters, LocationDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the locked state, last locked date, and last locked by user for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return LockDataWrapper
     */
    LockDataWrapper getLock(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LockDataWrapper) get(HttpUrls.ITEMS_ID_LOCK, pathParameters, queryParameters, LockDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the parent item for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataWrapper
     */
    ItemDataWrapper getParent(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataWrapper) get(HttpUrls.ITEMS_ID_PARENT, pathParameters, queryParameters, ItemDataWrapper.class, poolUtils, headers);
    }


    /**
     * GET: Get all synchronized items for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataListWrapper
     */
    ItemDataListWrapper getSyncedItems(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.ITEMS_ID_SYNCED_ITEMS, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the sync status for the synced item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. syncedItemId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return SyncStatusDataWrapper
     */
    SyncStatusDataWrapper getSyncStatus(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (SyncStatusDataWrapper) get(HttpUrls.ITEMS_ID_SYNCED_ITEMS_ID_STATUS, pathParameters, queryParameters, SyncStatusDataWrapper.class, poolUtils, headers);
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
        return (TagDataListWrapper) get(HttpUrls.ITEMS_ID_TAGS, pathParameters, queryParameters, TagDataListWrapper.class, poolUtils, headers);
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
     *
     * @return TagDataWrapper
     */
    TagDataWrapper getTag(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TagDataWrapper) get(HttpUrls.ITEMS_ID_TAGS_ID, pathParameters, queryParameters, TagDataWrapper.class, poolUtils, headers);
    }


    /**
     * GET: Get all upstream related items for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataListWrapper
     */
    ItemDataListWrapper getUpstreamRelated(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.ITEMS_ID_UPSTREAM_RELATED, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all upstream relationships for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return RelationshipDataListWrapper
     */
    RelationshipDataListWrapper getUpstreamRelationships(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipDataListWrapper) get(HttpUrls.ITEMS_ID_UPSTREAM_RELATIONSHIPS, pathParameters, queryParameters, RelationshipDataListWrapper.class, poolUtils, headers);
    }


    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return VersionDataWrapper
     */
    VersionDataWrapper getVersion(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataWrapper) get(HttpUrls.ITEMS_ID_VERSIONS_NUM, pathParameters, queryParameters, VersionDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return VersionedItemDataWrapper
     */
    VersionedItemDataWrapper getVersionedItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionedItemDataWrapper) get(HttpUrls.ITEMS_ID_VERSIONS_NUM_ITEM, pathParameters, queryParameters, VersionedItemDataWrapper.class, poolUtils, headers);
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
     *
     * @return VersionDataListWrapper
     */
    VersionDataListWrapper getVersions(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataListWrapper) get(HttpUrls.ITEMS_ID_VERSIONS, pathParameters, queryParameters, VersionDataListWrapper.class, poolUtils, headers);
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
     *
     * @return WorkflowTransitionDataListWrapper
     */
    WorkflowTransitionDataListWrapper getWorkflowTransitionOptions(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (WorkflowTransitionDataListWrapper) get(HttpUrls.ITEMS_ID_WORKFLOW_TRANSITION_OPTIONS, pathParameters, queryParameters, WorkflowTransitionDataListWrapper.class, poolUtils, headers);
    }

    /**
     * PATCH: Update the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: array[RequestPatchOperation]
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse patchItem(List<RequestPatchOperation> operations, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) patch(HttpUrls.ITEMS_ID_PATCH, pathParameters, FastJsonUtils.toJSONNoFeatures(operations), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: array[RequestPatchOperation]
     *
     * @return AbstractRestResponse
     */
    public AbstractRestResponse updateItem(RequestItem item, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ITEMS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(item), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing attachment from the item
     * Path parameters:
     * 1. id (required)
     * 2. attachmentId (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse removeAttachment(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.ITEMS_ID_ATTACHMENTS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing tag from the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. tagId (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse removeTag(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.ITEMS_ID_TAGS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Body: RequestLink
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse updateLink(RequestLink link, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ITEMS_ID_LINKS_ID_PUT, pathParameters, FastJsonUtils.convertObjectToJSON(link), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the location for the item with the specified ID as an asynchronous request (a successful response signifies that the work was started and a work identifier is given. This identifier will be used in a future feature).
     * Any child items are moved along with this item. Note that this currently only supports moving items between projects
     * Path parameters:
     * 1. id (required)
     * Body: RequestMoveLocation
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse updateLocation(RequestMoveLocation moveLocation, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ITEMS_ID_LOCATION_PUT, pathParameters, FastJsonUtils.convertObjectToJSON(moveLocation), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLock
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse lockItem(RequestLock lock, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ITEMS_ID_LOCK_PUT, pathParameters, FastJsonUtils.convertObjectToJSON(lock), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
