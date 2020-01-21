package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.itemtypes.ItemTypeDataListWrapper;
import com.amt.jama.core.po.itemtypes.ItemTypeDataWrapper;
import com.amt.jama.core.po.request.RequestItemType;
import com.amt.jama.core.po.request.RequestItemTypeField;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class ItemTypeClient extends BaseClient {

    ItemTypeClient(String restVersion) {
        super(restVersion);
    }

    /**
     * POST: Create a new item type field for the item type with the specified ID
     * Path parameters:
     * 1. itemTypeId  (required)
     * Body: RequestItemTypeField
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse addItemTypeField(RequestItemTypeField itemTypeField, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ITEM_TYPE_ID_FIELDS_POST, pathParameters, FastJsonUtils.convertObjectToJSON(itemTypeField), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new item type
     * Body: RequestItemType
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse createItemType(RequestItemType itemType, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ITEM_TYPES_POST, null, FastJsonUtils.convertObjectToJSON(itemType), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get the item type with the specified ID
     * Path parameters:
     * 1. itemTypeId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemTypeDataWrapper
     */
    ItemTypeDataWrapper getItemType(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemTypeDataWrapper) get(HttpUrls.ITEM_TYPES_ID, pathParameters, queryParameters, ItemTypeDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all item types
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemTypeDataListWrapper
     */
    ItemTypeDataListWrapper getItemTypes(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemTypeDataListWrapper) get(HttpUrls.ITEM_TYPES, null, queryParameters, ItemTypeDataListWrapper.class, poolUtils, headers);
    }
}
