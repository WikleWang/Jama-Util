package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.baselines.BaselineDataListWrapper;
import com.amt.jama.core.po.baselines.BaselineDataWrapper;
import com.amt.jama.core.po.baselines.BaselineItemDataListWrapper;
import com.amt.jama.core.po.baselines.BaselineItemDataWrapper;
import com.amt.jama.core.po.version.VersionedRelationshipDataListWrapper;

import java.util.Map;

public class BaselineClient extends BaseClient {


    BaselineClient(String restVersion) {
        super(restVersion);
    }

    /**
     * GET: Get all baselines in the project with the specified ID
     * Query parameters:
     * 1. project (required)
     * 2. startAt (optional)
     * 3. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return BaselineDataListWrapper
     */
    BaselineDataListWrapper getBaselines(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (BaselineDataListWrapper) get(HttpUrls.BASELINES, null, queryParameters, BaselineDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the baseline with the specified ID
     * Path parameters:
     * 1. baselineId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return BaselineDataWrapper
     */
    BaselineDataWrapper getBaseline(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (BaselineDataWrapper) get(HttpUrls.BASELINES_ID, pathParameters, queryParameters, BaselineDataWrapper.class, poolUtils, headers);
    }

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
     *
     * @return BaselineItemDataListWrapper
     */
    BaselineItemDataListWrapper getBaselineVersionItems(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (BaselineItemDataListWrapper) get(HttpUrls.BASELINES_ID_ITEMS, pathParameters, queryParameters, BaselineItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the baseline item with the specified ID in a baseline with the specified ID
     * Path parameters:
     * 1. baselineId (required)
     * 2. itemId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return BaselineItemDataWrapper
     */
    BaselineItemDataWrapper getBaselineVersionItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (BaselineItemDataWrapper) get(HttpUrls.BASELINES_ID_ITEMS_ID, pathParameters, queryParameters, BaselineItemDataWrapper.class, poolUtils, headers);
    }

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
     *
     * @return VersionedRelationshipDataListWrapper
     */
    VersionedRelationshipDataListWrapper getBaselineVersionRelationships(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionedRelationshipDataListWrapper) get(HttpUrls.BASELINES_ID_ITEMS_ID_RELATIONSHIPS, pathParameters, queryParameters, VersionedRelationshipDataListWrapper.class, poolUtils, headers);
    }
}
