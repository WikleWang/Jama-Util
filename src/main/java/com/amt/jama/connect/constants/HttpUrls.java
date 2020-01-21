package com.amt.jama.connect.constants;

import java.util.Map;

public class HttpUrls {

    /**
     * GET: Search for items, test plans, test cycles, test runs, or attachments
     * Query parameters:
     * 1. project(optional)
     * 2. itemType (optional)
     * 3. documentKey (optional)
     * 4. release (optional)
     * 5. createdDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 6. modifiedDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 7. lastActivityDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 8. contains (optional)
     * Filter on the text contents of the item. Strings taken literally. Multiple 'contains' values will be bitwise ORed.
     * 9. sortBy (optional)
     * Sort orders can be added with the name of the field by which to sort, followed by .asc or .desc (e.g. 'name.asc' or 'modifiedDate.desc'). If not set, this defaults to sorting by sequence.asc and then documentKey.asc
     * 10. startAt (optional)
     * 11. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 12. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ABSTRACT_ITEMS = "/abstractitems";

    /**
     * GET: Get any item, test plan, test cycle, test run, or attachment with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ABSTRACT_ITEMS_ID = "/abstractitems/{id}";

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
    public static final String ABSTRACT_ITEMS_ID_VERSION = "/abstractitems/{id}/versions";


    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ABSTRACT_ITEMS_ID_VERSION_NUM = "/abstractitems/{id}/versions/{versionNum}";

    /**
     * GET: Get the snapshot of the item at the specified version
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ABSTRACT_ITEMS_ID_VERSION_NUM_ITEM = "/abstractitems/{id}/versions/{versionNum}/versioneditem";


    /**
     * GET: Get all versioned relationships that were associated to the item at the specified time
     * Path parameters:
     * id (required)
     * Query parameters:
     * 1. timestamp (required)
     * Get relationships for the specified item at this date and time. Requires ISO8601 formatting (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 2. startAt (optional)
     * 3.  maxResults (optional)
     * not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ABSTRACT_ITEMS_ID_VERSION_RELATIONSHIPS = "/abstractitems/{id}/versionedrelationships";


    /**
     * GET: Get all activities in the project with the specified ID
     * Query parameters:
     * 1. projectId  (required)
     * 2. eventType (optional)
     * Event type to filter on. More than one event type can be chosen
     * 3. objectType (optional)
     * Object type to filter on. More than one object type can be chosen
     * 4. itemType (optional)
     * ID of item type to filter on. More than one item type can be chosen
     * 5. date (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 6. deleteEvents (optional)
     * Get item delete events only
     * 7. startAt (optional)
     * 8. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 9. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ACTIVITIES = "/activities";

    /**
     * GET: Get the activity with the specified ID
     * Path parameters:
     * 1. activityId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ACTIVITIES_ID = "/activities/{activityId}";

    /**
     * GET: Get all items affected by the activity with the specified ID
     * Path parameters:
     * 1. activityId  (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ACTIVITIES_ID_AFFECTED_ITEMS = "/activities/{activityId}/affecteditems";

    /**
     * POST: Restore item(s) associated with a delete activity.
     * Path parameters:
     * 1. activityId  (required)
     */
    public static final String ACTIVITIES_ID_RESTORE_POST = "/activities/{activityId}/restore";


    /**
     * GET: Get the attachment with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID = "/attachments/{attachmentId}";

    /**
     * GET: Download attachment file from the attachment with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     */
    public static final String ATTACHMENTS_ID_FILE = "/attachments/{attachmentId}/file";

    /**
     * GET: Get all comments for the item with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID_COMMENTS = "/attachments/{attachmentId}/comments";

    /**
     * GET: Get the locked state, last locked date, and last locked by user for the item with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID_LOCK = "/attachments/{attachmentId}/lock";

    /**
     * GET: Get all versions for the item with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID_VERSIONS = "/attachments/{attachmentId}/versions";
    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID_VERSIONS_NUM = "/attachments/{attachmentId}/versions/{versionNum}";

    /**
     * GET: Get the snapshot of the attachment at the specified version
     * Path parameters:
     * 1. attachmentId  (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID_VERSIONS_NUM_ITEM = "/attachments/{attachmentId}/versions/{versionNum}/versioneditem";

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     * Body: RequestLock
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID_LOCK_PUT = "/attachments/{attachmentId}/lock";

    /**
     * PUT: Upload attachment file to the attachment with the specified ID
     * Path parameters:
     * 1. attachmentId  (required)
     * Content-Type: multipart/form-data
     * Body: FormDataMultiPart
     * Links to include as full objects in the linked map
     */
    public static final String ATTACHMENTS_ID_FILE_PUT = "/attachments/{attachmentId}/file";

    /**
     * GET: Get all baselines in the project with the specified ID
     * Query parameters:
     * 1. project (required)
     * 2. startAt (optional)
     * 3. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String BASELINES = "/baselines";

    /**
     * GET: Get the baseline with the specified ID
     * Path parameters:
     * 1. baselineId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String BASELINES_ID = "/baselines/{baselineId}";

    /**
     * GET: Get all baseline items in a baseline with the specified ID
     * Path parameters:
     * 1. baselineId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String BASELINES_ID_ITEMS = "/baselines/{baselineId}/versioneditems";

    /**
     * GET: Get the baseline item with the specified ID in a baseline with the specified ID
     * Path parameters:
     * 1. baselineId (required)
     * 2. itemId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String BASELINES_ID_ITEMS_ID = "/baselines/{baselineId}/versioneditems/{itemId}";

    /**
     * GET: Get all versioned relationships for the item in the baseline
     * Path parameters:
     * 1. baselineId (required)
     * 2. itemId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String BASELINES_ID_ITEMS_ID_RELATIONSHIPS = "/baselines/{baselineId}/versioneditems/{itemId}/versionedrelationships";

    /**
     * GET: Get all comments viewable by the current user
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. rootCommentsOnly (optional)
     * whether to show only root comments; true to get only root comments, without their comment replies, default value: false
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String COMMENTS = "/comments";

    /**
     * POST: Create a new comment
     * Body: RequestComment
     */
    public static final String COMMENTS_POST = "/comments";

    /**
     * GET: Get all comments viewable by the current user
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String COMMENTS_ID = "/comments/{id}";

    /**
     * GET: Get all reply comments for the comment with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String COMMENTS_ID_REPLIES = "/comments/{id}/replies";

    /**
     * GET: Download attachment file from the attachment with the specified Jama URL
     * Query parameters:
     * 1. url (required)
     */
    public static final String FILES = "/files";

    /**
     * GET:  Get all filters viewable by the current user
     * Query parameters:
     * 1. project (optional)
     * 2. author (optional)
     * 3. filterScope (optional)
     * Filter scope. More than one scope can be selected
     * 4. startAt (optional)
     * 5. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 6. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String FILTERS = "/filters";
    /**
     * GET: Get the filter with the specified ID
     * Path parameters:
     * 1. filterId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String FILTERS_ID = "/filters/{filterId}";

    /**
     * GET: Get all result items for the filter with the specified ID
     * Path parameters:
     * 1. filterId (required)
     * Query parameters:
     * 1. project (optional)
     * Use only for filters that run on any project, where \"projectScope\" is \"CURRENT\"
     * 2. lastActivityDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 3. startAt (optional)
     * 4. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 5. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String FILTERS_ID_RESULTS = "/filters/{filterId}/results";

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
    public static final String ITEMS = "/items";

    /**
     * POST: Create a new item
     * Query parameters:
     * 1. setGlobalIdManually (optional)
     * This value must be set to true if you attempt to manually set the Global ID field of an item
     * Body: RequestItem
     */
    public static final String ITEMS_POST = "/items";

    /**
     * GET: Get the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID = "/items/{id}";

    /**
     * DELETE: Delete the item with the specified ID (item becomes inactive and can be un-deleted if necessary)
     * Path parameters:
     * 1. id (required)
     */
    public static final String ITEMS_ID_DELETE = "/items/{id}";


    /**
     * PATCH: Update the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: array[RequestPatchOperation]
     */
    public static final String ITEMS_ID_PATCH = "/items/{id}";

    /**
     * PUT: Update the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItem
     */
    public static final String ITEMS_ID_PUT = "/items/{id}";

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
     */
    public static final String ITEMS_ID_ACTIVITIES = "/items/{id}/activities";

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
    public static final String ITEMS_ID_ATTACHMENTS = "/items/{id}/attachments";

    /**
     * POST: Add an existing attachment to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemAttachment
     */
    public static final String ITEMS_ID_ATTACHMENTS_POST = "/items/{id}/attachments";

    /**
     * DELETE: Remove an existing attachment from the item
     * Path parameters:
     * 1. id (required)
     * 2. attachmentId (required)
     */
    public static final String ITEMS_ID_ATTACHMENTS_ID_DELETE = "/items/{id}/attachments/{attachmentId}";

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
     */
    public static final String ITEMS_ID_CHILDREN = "/items/{id}/children";

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
     */
    public static final String ITEMS_ID_DOWNSTREAM_RELATED = "/items/{id}/downstreamrelated";

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
     */
    public static final String ITEMS_ID_DOWNSTREAM_RELATIONSHIPS = "/items/{id}/downstreamrelationships";

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
     */
    public static final String ITEMS_ID_UPSTREAM_RELATED = "/items/{id}/upstreamrelated";

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
     */
    public static final String ITEMS_ID_UPSTREAM_RELATIONSHIPS = "/items/{id}/upstreamrelationships";
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
    public static final String ITEMS_ID_COMMENTS = "/items/{id}/comments";

    /**
     * POST: Create a duplicate of item
     * Path parameters:
     * 1. id (required)
     * Body: DuplicateConfig
     */
    public static final String ITEMS_ID_DUPLICATE_POST = "/items/{id}/duplicate";

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
    public static final String ITEMS_ID_LINKS = "/items/{id}/links";


    /**
     * POST: Create a new link for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: ResponseLink
     */
    public static final String ITEMS_ID_LINKS_POST = "/items/{id}/links";

    /**
     * GET: Get the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_LINKS_ID = "/items/{id}/links/{linkId}";

    /**
     * DELETE: Delete the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     */
    public static final String ITEMS_ID_LINKS_ID_DELETE = "/items/{id}/links/{linkId}";

    /**
     * PUT: Update the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Body: RequestLink
     */
    public static final String ITEMS_ID_LINKS_ID_PUT = "/items/{id}/links/{linkId}";

    /**
     * GET: Get location for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_LOCATION = "/items/{id}/location";

    /**
     * PUT: Update the location for the item with the specified ID as an asynchronous request (a successful response signifies that the work was started and a work identifier is given. This identifier will be used in a future feature).
     * Any child items are moved along with this item. Note that this currently only supports moving items between projects
     * Path parameters:
     * 1. id (required)
     * Body: RequestMoveLocation
     */
    public static final String ITEMS_ID_LOCATION_PUT = "/items/{id}/location";

    /**
     * GET: Get the locked state, last locked date, and last locked by user for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_LOCK = "/items/{id}/lock";

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLock
     */
    public static final String ITEMS_ID_LOCK_PUT = "/items/{id}/lock";

    /**
     * GET: Get the parent item for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_PARENT = "/items/{id}/parent";

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
     */
    public static final String ITEMS_ID_SYNCED_ITEMS = "/items/{id}/synceditems";

    /**
     * POST: Add an existing item to the Global ID pool of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemSyncedItem
     */
    public static final String ITEMS_ID_SYNCED_ITEMS_POST = "/items/{id}/synceditems";

    /**
     * GET: Get the sync status for the synced item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. syncedItemId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_SYNCED_ITEMS_ID_STATUS = "/items/{id}/synceditems/{syncedItemId}/syncstatus";

    /**
     * DELETE: Remove an existing item from the Global ID pool of the item with the specified ID (break sync)
     * Path parameters:
     * 1. id (required)
     * 2. syncedItemId (required)
     */
    public static final String ITEMS_ID_SYNCED_ITEMS_ID_DELETE = "/items/{id}/synceditems/{syncedItemId}";


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
    public static final String ITEMS_ID_TAGS = "/items/{id}/tags";

    /**
     * POST: Add an existing tag to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemTag
     */
    public static final String ITEMS_ID_TAGS_POST = "/items/{id}/tags";

    /**
     * GET: Get the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. tagId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_TAGS_ID = "/items/{id}/tags/{tagId}";

    /**
     * DELETE: Remove an existing tag from the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. tagId (required)
     */
    public static final String ITEMS_ID_TAGS_ID_DELETE = "/items/{id}/tags/{tagId}";

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
    public static final String ITEMS_ID_VERSIONS = "/items/{id}/versions";

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_VERSIONS_NUM = "/items/{id}/tags/{versionNum}";

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEMS_ID_VERSIONS_NUM_ITEM = "/items/{id}/tags/{versionNum}/versioneditem";

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
    public static final String ITEMS_ID_WORKFLOW_TRANSITION_OPTIONS = "/items/{id}/workflowtransitionoptions";

    /**
     * POST: Executes a workflow transition for the item with the specified ID. Valid transitions can be found at /items/{id}/workflowtransitionoptions
     * Path parameters:
     * 1. id (required)
     * Body: RequestTransition
     */
    public static final String ITEMS_ID_WORKFLOW_TRANSITIONS_POST = "/items/{id}/workflowtransitions";


    /**
     * GET: Get all item types
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEM_TYPES = "/itemtypes";

    /**
     * POST: Create a new item type
     * Body: RequestItemType
     */
    public static final String ITEM_TYPES_POST = "/itemtypes";

    /**
     * GET: Get the item type with the specified ID
     * Path parameters:
     * 1. itemTypeId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String ITEM_TYPES_ID = "/itemtypes/{itemTypeId}";

    /**
     * POST: Create a new item type field for the item type with the specified ID
     * Path parameters:
     * 1. itemTypeId  (required)
     * Body: RequestItemTypeField
     */
    public static final String ITEM_TYPE_ID_FIELDS_POST = "/itemtypes/{itemTypeId}/fields";

    /**
     * GET: Get the pick list option with the specified ID
     * Path parameters:
     * 1. picklistOptionId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PICK_LIST_OPTIONS_ID = "/picklistoptions/{picklistOptionId}";

    /**
     * PUT: Update the pick list option with the specified ID
     * Path parameters:
     * 1. picklistOptionId  (required)
     * Body: RequestPickListOption
     */
    public static final String PICK_LIST_OPTIONS_ID_PUT = "/picklistoptions/{picklistOptionId}";

    /**
     * GET: Get all pick lists
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PICK_LISTS = "/picklists";

    /**
     * POST: Create a new pick list
     * Body: RequestPickList
     */
    public static final String PICK_LISTS_POST = "/picklists";

    /**
     * GET: Get the pick list with the specified ID
     * Path parameters:
     * 1. picklistId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PICK_LISTS_ID = "/picklists/{picklistId}";

    /**
     * GET: Delete the pick list
     * Path parameters:
     * 1. picklistId  (required)
     */
    public static final String PICK_LISTS_ID_DELETE = "/picklists/{picklistId}";

    /**
     * GET: Get all pick list options for the pick list with the specified ID
     * Path parameters:
     * 1. picklistId  (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PICK_LISTS_ID_OPTIONS = "/picklists/{picklistId}/options";

    /**
     * POST: Create a new pick list option for the pick list with the specified ID
     * Path parameters:
     * 1. picklistId  (required)
     * Body: RequestPickListOption
     */
    public static final String PICK_LISTS_ID_OPTIONS_POST = "/picklists/{picklistId}/options";

    /**
     * GET: Get all projects
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PROJECTS = "/projects";

    /**
     * POST: Create a new project
     * Body: RequestProject
     */
    public static final String PROJECTS_POST = "/projects";

    /**
     * GET: Get the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PROJECTS_ID = "/projects/{projectId}";

    /**
     * PUT: Update the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Body: RequestProject
     */
    public static final String PROJECTS_ID_PUT = "/projects/{projectId}";

    /**
     * GET: Get all item types for the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PROJECTS_ID_ITEM_TYPES = "/projects/{projectId}/itemtypes";

    /**
     * GET: Add an Item Type to a Project
     * Path parameters:
     * 1. projectId (required)
     * 2. itemTypeId (required)
     */
    public static final String PROJECTS_ITEM_TYPES_ID_PUT = "/projects/{projectId}/itemtypes/{itemTypeId}";

    /**
     * DELETE: Remove an Item Type to a Project
     * Path parameters:
     * 1. projectId (required)
     * 2. itemTypeId (required)
     */
    public static final String PROJECTS_ITEM_TYPES_ID_DELETE = "/projects/{projectId}/itemtypes/{itemTypeId}";

    /**
     * GET: Get all tags for the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String PROJECTS_ID_TAGS = "/projects/{projectId}/tags";


    /**
     * POST:  Create a new attachment in the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Body: RequestAttachment
     */
    public static final String PROJECTS_ID_ATTACHMENTS_POST = "/projects/{projectId}/attachments";
    /**
     * GET: Get all relationships in the project with the specified ID
     * Query parameters:
     * 1. project (required)
     * 2. startAt (optional)
     * 3. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String RELATIONSHIPS = "/relationships";

    /**
     * POST: Create a new relationship
     * Body:RequestRelationship
     */
    public static final String RELATIONSHIPS_POST = "/relationships";

    /**
     * GET: Get the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String RELATIONSHIPS_ID = "/relationships/{relationshipId}";

    /**
     * PUT: Update the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     * Body: RequestRelationship
     */
    public static final String RELATIONSHIPS_ID_PUT = "/relationships/{relationshipId}";

    /**
     * DELETE: Delete the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     */
    public static final String RELATIONSHIPS_ID_DELETE = "/relationships/{relationshipId}";

    /**
     * DELETE: Remove an existing suspect link for the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     */
    public static final String RELATIONSHIPS_ID_SUSPECT_DELETE = "/relationships/{relationshipId}/suspect";

    /**
     * GET: Get all relationship types
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String RELATIONSHIPS_TYPES = "/relationshiptypes";

    /**
     * GET: Get the relationship type with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. timestamp (optional)
     * Get relationship type at this date and time. Requires ISO8601 formatting (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 2. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String RELATIONSHIPS_TYPES_ID = "/relationshiptypes/{id}";


    /**
     * GET: Get all releases in the project with the specified ID
     * Query parameters:
     * 1. project (required)
     * 2. startAt (optional)
     * 3. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String RELEASES = "/releases";

    /**
     * POST: Create a new release
     * Body: RequestRelease
     */
    public static final String RELEASES_POST = "/releases";

    /**
     * GET: Get all releases in the project with the specified ID
     * Path parameters:
     * 1. releaseId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String RELEASES_ID = "/releases/{releaseId}";

    /**
     * PUT: Update the release with the specified ID
     * Path parameters:
     * 1. releaseId (required)
     * Body: RequestRelease
     */
    public static final String RELEASES_ID_PUT = "/releases/{releaseId}";

    /**
     * GET: Get the current CORS domain whitelist
     */
    public static final String SYSTEM_SETTING_CORS_DOMAINS = "/system/settings/corsdomains";

    /**
     * POST: Update CORS domain whitelist
     */
    public static final String SYSTEM_SETTING_CORS_DOMAINS_POST = "/system/settings/corsdomains";

    /**
     * GET: Get all tags for the project with the specified ID
     * Query parameters:
     * 1. project (required)
     * 2. startAt (optional)
     * 3. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TAGS = "/tags";
    /**
     * POST: Create a new tag in the project with the specified ID
     * Body: RequestTag
     */
    public static final String TAGS_POST = "/tags";

    /**
     * DELETE: Delete the tag with the specified ID
     */
    public static final String TAGS_DELETE = "/tags";

    /**
     * GET: Get all releases in the project with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TAGS_ID = "/tags/{id}";

    /**
     * PUT: Update the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    public static final String TAGS_ID_PUT = "/tags/{id}";

    /**
     * GET: Get all items that have the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TAGS_ID_ITEMS = "/tags/{id}/items";

    /**
     * GET: Get the test cycle with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_CYCLES_ID = "/testcycles/{testCycleId}";

    /**
     * DELETE: Delete the test cycle with the specified ID, including the test runs in the test cycle
     * Path parameters:
     * 1. testCycleId (required)
     */
    public static final String TEST_CYCLES_ID_DELETE = "/testcycles/{testCycleId}";

    /**
     * PATCH: Update the test cycle with the specified ID, including regenerating the test runs in the test cycle
     * Path parameters:
     * 1. testCycleId (required)
     * Body: array[RequestPatchOperation]
     */
    public static final String TEST_CYCLES_ID_PATCH = "/testcycles/{testCycleId}";

    /**
     * PUT: Update the test cycle with the specified ID, including regenerating the test runs in the test cycle
     * Path parameters:
     * 1. testCycleId (required)
     * Body: RequestTestCycle
     */
    public static final String TEST_CYCLES_ID_PUT = "/testcycles/{testCycleId}";
    /**
     * GET: Get the test cycle test group for the test cycle with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * 2. testGroupId (required)
     * Get the test group with the specified ID
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_CYCLES_ID_TEST_GROUP_ID = "/testcycles/{testCycleId}/testgroup/{testGroupId}";

    /**
     * GET: Get all test runs for the test cycle with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_CYCLES_ID_TEST_RUNS = "/testcycles/{testCycleId}/testruns";

    /**
     * GET: Get all versions for the item with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_CYCLES_ID_VERSIONS = "/testcycles/{testCycleId}/versions";
    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_CYCLES_ID_VERSIONS_NUM = "/testcycles/{testCycleId}/versions/{versionNum}";


    /**
     * GET: Get the snapshot of the test cycle at the specified version
     * Path parameters:
     * 1. testCycleId (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_CYCLES_ID_VERSIONS_NUM_ITEM = "/testcycles/{testCycleId}/versions/{versionNum}/versioneditem";

    /**
     * POST: Add an existing attachment to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemAttachment
     */
    public static final String TEST_PLANS_ID_ATTACHMENTS_POST = "/testplans/{id}/attachments";

    /**
     * POST: Create a new link for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLink
     */
    public static final String TEST_PLANS_ID_LINKS_POST = "/testplans/{id}/links";

    /**
     * POST: Add an existing tag to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemTag
     */
    public static final String TEST_PLANS_ID_TAGS_POST = "/testplans/{id}/tags";

    /**
     * POST: Create a new test group to the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestGroup
     */
    public static final String TEST_PLANS_ID_TEST_GROUPS_POST = "/testplans/{id}/testgroups";

    /**
     * POST: Create a new test cycle
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestCycle
     */
    public static final String TEST_PLANS_ID_TEST_CYCLES_POST = "/testplans/{id}/testcycles";

    /**
     * POST: Create a new test plan
     * Body: RequestTestPlan
     */
    public static final String TEST_PLANS_POST = "/testplans";

    /**
     * DELETE: Delete the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     */
    public static final String TEST_PLANS_ID_LINKS_ID_DELETE = "/testplans/{id}/links/{linkId}";
    /**
     * DELETE: Remove an existing test case from the test group
     * Path parameters:
     * 1. id (required)
     * 2. testGroupId (required)
     * 2. testCaseId (required)
     */
    public static final String TEST_PLANS_ID_TEST_GROUP_ID_TEST_CASE_ID_DELETE = "/testplans/{id}/testgroups/{testGroupId}/testcases/{testCaseId}";

    /**
     * DELETE: Delete the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. testGroupId (required)
     */
    public static final String TEST_PLANS_ID_TEST_GROUP_ID_DELETE = "/testplans/{id}/testgroups/{testGroupId}";

    /**
     * DELETE: Delete the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    public static final String TEST_PLANS_ID_DELETE = "/testplans/{id}";


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
    public static final String TEST_PLANS_ID_ACTIVITIES = "/testplans/{id}/activities";

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
    public static final String TEST_PLANS_ID_ATTACHMENTS = "/testplans/{id}/attachments";

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
    public static final String TEST_PLANS_ID_DOWN_STREAM_RELATED = "/testplans/{id}/downstreamrelated";


    /**
     * GET: Get all downstream relationships for the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_DOWN_STREAM_RELATIONSHIPS = "/testplans/{id}/downstreamrelationships";

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
    public static final String TEST_PLANS_ID_COMMENTS = "/testplans/{id}/comment";

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
    public static final String TEST_PLANS_ID_LINKS = "/testplans/{id}/links";

    /**
     * GET: Get the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_LINKS_ID = "/testplans/{id}/links/{linkId}";

    /**
     * GET: Get the locked state, last locked date, and last locked by user for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_LOCK = "/testplans/{id}/lock";

    /**
     * GET: Get the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. tagId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_TAGS_ID = "/testplans/{id}/tags/{tagId}";

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
    public static final String TEST_PLANS_ID_TAGS = "/testplans/{id}/tags";

    /**
     * GET: Get the test case with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. testGroupId (required)
     * 3. testCaseId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_TEST_GROUPS_ID_TEST_CASES_ID = "/testplans/{id}/testgroups/{testGroupId}/testcases/{testCaseId}";


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
    public static final String TEST_PLANS_ID_TEST_GROUPS_ID_TEST_CASES = "/testplans/{id}/testgroups/{testGroupId}/testcases";

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
    public static final String TEST_PLANS_ID_TEST_CYCLES = "/testplans/{id}/testcycles";

    /**
     * GET: Get the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testGroupId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_TEST_GROUPS_ID = "/testplans/{id}/testgroups/{testGroupId}";

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
    public static final String TEST_PLANS_ID_TEST_GROUPS = "/testplans/{id}/testgroups";

    /**
     * GET: Get the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID = "/testplans/{id}";

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
    public static final String TEST_PLANS = "/testplans";

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
    public static final String TEST_PLANS_ID_UPSTREAM_RELATED = "/testplans/{id}/upstreamrelated";

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
    public static final String TEST_PLANS_ID_UPSTREAM_RELATIONSHIPS = "/testplans/{id}/upstreamrelationships";

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_VERSIONS_NUM = "/testplans/{id}/versions/{versionNum}";

    /**
     * GET: Get the snapshot of the test plan at the specified version
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_PLANS_ID_VERSIONS_NUM_ITEM = "/testplans/{id}/versions/{versionNum}/versioneditem";

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
    public static final String TEST_PLANS_ID_VERSIONS = "/testplans/{id}/versions";

    /**
     * PATCH: Update the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: array[RequestPatchOperation]
     */
    public static final String TEST_PLANS_ID_PATCH = "/testplans/{id}";


    /**
     * POST: Add an existing test case to the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testGroupId (required)
     * Body: RequestTestGroupTestCase
     */
    public static final String TEST_PLANS_ID_TEST_GROUPS_ID_TEST_CASE_POST = "/testplans/{id}/testgroups/{testGroupId}/testcases";


    /**
     * DELETE: Remove an existing attachment from the item
     * Path parameters:
     * 1. id (required)
     * 1. attachmentId (required)
     */
    public static final String TEST_PLANS_ID_ATTACHMENTS_ID_DELETE = "/testplans/{id}/attachments/{attachmentId}";

    /**
     * DELETE: Remove an existing tag from the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. tagId (required)
     */
    public static final String TEST_PLANS_ID_TAGS_ID_DELETE = "/testplans/{id}/tags/{tagId}";

    /**
     * PUT: Update the archived status of the test plan
     * Path parameters:
     * 1. id (required)
     * Body: RequestArchivedStatus
     */
    public static final String TEST_PLANS_ID_ARCHIVED_PUT = "/testplans/{id}/archived";

    /**
     * PUT: Update the archived status of the test plan
     * Path parameters:
     * 1. id (required)
     * 1. linkId (required)
     * Body: RequestLink
     */
    public static final String TEST_PLANS_ID_LINKS_ID_PUT = "/testplans/{id}/links/{linkId}";

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLink
     */
    public static final String TEST_PLANS_ID_LOCK_PUT = "/testplans/{id}/lock";

    /**
     * PUT: Update the test group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 1. testGroup (required)
     * Body: RequestTestGroup
     */
    public static final String TEST_PLANS_ID_TEST_GROUPS_ID_PUT = "/testplans/{id}/testgroups/{testGroupId}";

    /**
     * PUT: Update the test plan with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestPlan
     */
    public static final String TEST_PLANS_ID_PUT = "/testplans/{id}";


    /**
     * POST: Add an existing attachment to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemAttachment
     */
    public static final String TEST_RUNS_ID_ATTACHMENTS_POST = "/testruns/{id}/attachments";
    /**
     * POST: Create a new link for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLink
     */
    public static final String TEST_RUNS_ID_LINKS_POST = "/testruns/{id}/links";
    /**
     * POST: Add an existing tag to the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestItemTag
     */
    public static final String TEST_RUNS_ID_TAGS_POST = "/testruns/{id}/tags";
    /**
     * DELETE: Delete the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     */
    public static final String TEST_RUNS_ID_LINKS_ID_DELETE = "/testruns/{id}/links/{linkId}";
    /**
     * DELETE: Delete the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    public static final String TEST_RUNS_ID_DELETE = "/testruns/{id}";

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
    public static final String TEST_RUNS = "/testruns";

    /**
     * GET: Get the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_RUNS_ID = "/testruns/{id}";

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
    public static final String TEST_RUNS_ID_ACTIVITIES = "/testruns/{id}/activities";

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
    public static final String TEST_RUNS_ID_ATTACHMENTS = "/testruns/{id}/attachments";

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
    public static final String TEST_RUNS_ID_DOWNSTREAM_RELATED = "/testruns/{id}/downstreamrelated";

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
    public static final String TEST_RUNS_ID_UPSTREAM_RELATED = "/testruns/{id}/upstreamrelated";

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
    public static final String TEST_RUNS_ID_DOWNSTREAM_RELATIONSHIPS = "/testruns/{id}/downstreamrelationships";

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
    public static final String TEST_RUNS_ID_UPSTREAM_RELATIONSHIPS = "/testruns/{id}/upstreamrelationships";

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
    public static final String TEST_RUNS_ID_COMMENTS = "/testruns/{id}/comments";

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
    public static final String TEST_RUNS_ID_LINKS = "/testruns/{id}/links";

    /**
     * GET: Get the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_RUNS_ID_LINKS_ID = "/testruns/{id}/links/{linkId}";

    /**
     * GET: Get the locked state, last locked date, and last locked by user for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_RUNS_ID_LOCK = "/testruns/{id}/lock";

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
    public static final String TEST_RUNS_ID_TAGS = "/testruns/{id}/tags";

    /**
     * GET: Get the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. tagId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_RUNS_ID_TAGS_ID = "/testruns/{id}/tags/{tagId}";

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
    public static final String TEST_RUNS_ID_VERSIONS = "/testruns/{id}/versions";

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_RUNS_ID_VERSIONS_NUM = "/testruns/{id}/versions/{versionNum}";

    /**
     * GET: Get the snapshot of the test run at the specified version
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String TEST_RUNS_ID_VERSIONS_NUM_ITEM = "/testruns/{id}/versions/{versionNum}/versioneditem";

    /**
     * POST: Delete the current test run and then create a new test run based on the latest test case version.
     * Path parameters:
     * 1. id (required)
     */
    public static final String TEST_RUNS_ID_UPDATED_TEST_CASE_POST = "/testruns/{id}/updatedtestcase";

    /**
     * PATCH: Update the execution results for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: array[RequestPatchOperation]
     */
    public static final String TEST_RUNS_ID_PATCH = "/testruns/{id}";

    /**
     * DELETE: Remove an existing attachment from the item
     * Path parameters:
     * 1. id (required)
     * 2. attachmentId (required)
     */
    public static final String TEST_RUNS_ID_ATTACHMENTS_ID_DELETE = "/testruns/{id}/attachments/{attachmentId}";

    /**
     * DELETE: Remove an existing tag from the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. tagId (required)
     */
    public static final String TEST_RUNS_ID_TAGS_ID_DELETE = "/testruns/{id}/tags/{tagId}";

    /**
     * PUT: Update the link with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. linkId (required)
     * Body: RequestLink
     */
    public static final String TEST_RUNS_ID_LINKS_ID_PUT = "/testruns/{id}/links/{linkId}";

    /**
     * PUT: Update the locked state of the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestLock
     */
    public static final String TEST_RUNS_ID_LOCK_PUT = "/testruns/{id}/lock";

    /**
     * PUT: Update the execution results for the test run with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestTestRun
     */
    public static final String TEST_RUNS_ID_PUT = "/testruns/{id}";

    /**
     * GET: Create a new user group
     * Body: RequestUserGroup
     */
    public static final String USER_GROUPS_POST = "/usergroups";

    /**
     * POST: Add an existing user to the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestGroupUser
     */
    public static final String USER_GROUPS_ID_USERS_POST = "/usergroups/{id}/users";

    /**
     * DELETE: Delete the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    public static final String USER_GROUPS_ID_DELETE = "/usergroups/{id}";


    /**
     * GET: Get all user groups
     * Query parameters:
     * 1. project (optional)
     * 2. startAt (optional)
     * 3. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String USER_GROUPS = "/usergroups";

    /**
     * GET: Get the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String USER_GROUPS_ID = "/usergroups/{id}";

    /**
     * GET: Get all users for the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String USER_GROUPS_ID_USERS = "/usergroups/{id}/users";


    /**
     * DELETE: Remove an existing user from the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. userId (required)
     */
    public static final String USER_GROUPS_ID_USERS_ID_DELETE = "/usergroups/{id}/users/{userId}";

    /**
     * PUT: Update the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestUserGroup
     */
    public static final String USER_GROUPS_ID_PUT = "/usergroups/{id}";

    /**
     * POST: Create a new user
     * Body: RequestUser
     */
    public static final String USERS_POST = "/users";

    /**
     * GET: Get all users
     * Query parameters:
     * 1. username (optional)
     * 2. email (optional)
     * 3. firstName (optional)
     * 4. lastName (optional)
     * 5. licenseType (optional)
     * 6. includeInactive (optional)
     * 7. startAt (optional)
     * 8. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 9. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String USERS = "/users";

    /**
     * GET: Get the user with the specified ID
     * Path parameters:
     * 1. userId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String USERS_ID = "/users/{userId}";

    /**
     * GET: Gets the current user
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String USERS_CURRENT = "/users/current";

    /**
     * GET: Gets the current user's favorite filters
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    public static final String USERS_CURRENT_FAVORITE_FILTERS = "/users/current/favoritefilters";

    /**
     * PUT: Update the user with the specified ID
     * Path parameters:
     * 1. userId (required)
     * Body: RequestUser
     */
    public static final String USERS_ID_PUT = "/users/{userId}";

    /**
     * PUT: Update the active status for the user with the specified ID
     * Path parameters:
     * 1. userId (required)
     * Body: RequestActiveStatus
     */
    public static final String USERS_ID_ACTIVE_PUT = "/users/{userId}/active";

    /**
     * 替换前缀
     */
    private static final String PREFIX = "{";

    /**
     * 替换后缀
     */
    private static final String SUFFIX = "}";


    /**
     * 处理替换URL
     *
     * @param url             url
     * @param pathParameters  pathParameters
     * @param queryParameters queryParameters
     * @return String
     */
    public static String getUrl(String url, Map<String, String> pathParameters, Map<String, String> queryParameters) {

        // 处理Path Parameters
        if (pathParameters != null && !pathParameters.isEmpty()) {
            for (Map.Entry<String, String> entry : pathParameters.entrySet()) {
                url = url.replace(PREFIX + entry.getKey() + SUFFIX, entry.getValue());
            }
        }

        // 处理Query Parameters
        if (queryParameters != null && !queryParameters.isEmpty()) {
            int index = 0;
            StringBuilder urlBuilder = new StringBuilder(url);
            for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
                String value = entry.getValue();
                if(value!=null && !"null".equals(value)) {
                    urlBuilder.append(index > 0 ? "&" : "?").append(entry.getKey()).append("=").append(entry.getValue());
                    index++;
                }
            }
            url = urlBuilder.toString();
        }

        return url;
    }


}


