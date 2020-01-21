package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.picklistoptions.PickListOptionDataListWrapper;
import com.amt.jama.core.po.picklistoptions.PickListOptionDataWrapper;
import com.amt.jama.core.po.picklists.PickListDataListWrapper;
import com.amt.jama.core.po.picklists.PickListDataWrapper;
import com.amt.jama.core.po.request.RequestPickList;
import com.amt.jama.core.po.request.RequestPickListOption;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class PickListClient extends BaseClient {

    PickListClient(String restVersion) {
        super(restVersion);
    }



    /**
     * GET: Get the pick list option with the specified ID
     * Path parameters:
     * 1. picklistOptionId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return PickListOptionDataWrapper
     */
    PickListOptionDataWrapper getPickListOption(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (PickListOptionDataWrapper) get(HttpUrls.PICK_LIST_OPTIONS_ID, pathParameters, queryParameters, PickListOptionDataWrapper.class, poolUtils, headers);
    }

    /**
     * PUT: Update the pick list option with the specified ID
     * Path parameters:
     * 1. picklistOptionId  (required)
     * Body: RequestPickListOption
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse updatePickListOption(RequestPickListOption pickListOption, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.PICK_LIST_OPTIONS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(pickListOption), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new pick list option for the pick list with the specified ID
     * Path parameters:
     * 1. picklistId  (required)
     * Body: RequestPickListOption
     *
     * @return CreatedResponse
     */
    CreatedResponse addPickListOption(RequestPickListOption pickListOption, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.PICK_LISTS_ID_OPTIONS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(pickListOption), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new pick list
     * Body: RequestPickList
     *
     * @return CreatedResponse
     */
    CreatedResponse createPickList(RequestPickList pickList, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.PICK_LISTS_POST, null, FastJsonUtils.toJSONNoFeatures(pickList), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * GET: Delete the pick list
     * Path parameters:
     * 1. picklistId  (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse deletePickList(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.PICK_LISTS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get the pick list with the specified ID
     * Path parameters:
     * 1. picklistId  (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return PickListDataWrapper
     */
    PickListDataWrapper getPickList(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (PickListDataWrapper) get(HttpUrls.PICK_LISTS_ID, pathParameters, queryParameters, PickListDataWrapper.class, poolUtils, headers);
    }

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
     *
     * @return PickListOptionDataListWrapper
     */
    PickListOptionDataListWrapper getPickListOptions(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (PickListOptionDataListWrapper) get(HttpUrls.PICK_LISTS_ID_OPTIONS, pathParameters, queryParameters, PickListOptionDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all pick lists
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return PickListDataListWrapper
     */
    PickListDataListWrapper getPickLists(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (PickListDataListWrapper) get(HttpUrls.PICK_LISTS, null, queryParameters, PickListDataListWrapper.class, poolUtils, headers);
    }
}
