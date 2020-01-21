package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.activities.ActivityDataListWrapper;
import com.amt.jama.core.po.activities.ActivityDataWrapper;
import com.amt.jama.core.po.items.ItemDataListWrapper;

import java.util.Map;

public class ActivityClient extends BaseClient {

    ActivityClient(String restVersion) {
        super(restVersion);
    }

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
    ActivityDataListWrapper getActivities(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ActivityDataListWrapper) get(HttpUrls.ACTIVITIES, null, queryParameters, ActivityDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the activity with the specified ID
     * Path parameters:
     * 1. activityId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    ActivityDataWrapper getActivity(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ActivityDataWrapper) get(HttpUrls.ACTIVITIES_ID, pathParameters, queryParameters, ActivityDataWrapper.class, poolUtils, headers);
    }

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
    ItemDataListWrapper getAffectedItems(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.ACTIVITIES_ID_AFFECTED_ITEMS, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * POST: Restore item(s) associated with a delete activity.
     * Path parameters:
     * 1. activityId  (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse restoreActivity(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) this.post(HttpUrls.ACTIVITIES_ID_RESTORE_POST, pathParameters, null, null, AbstractRestResponse.class, poolUtils, headers);
    }
}
