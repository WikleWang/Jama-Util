package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.items.ItemDataListWrapper;
import com.amt.jama.core.po.request.RequestTag;
import com.amt.jama.core.po.tags.TagDataListWrapper;
import com.amt.jama.core.po.tags.TagDataWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class TagClient extends BaseClient {

    TagClient(String restVersion) {
        super(restVersion);
    }

    /**
     * POST: Create a new tag in the project with the specified ID
     * Body: RequestTag
     *
     * @return AbstractRestResponse
     */
    CreatedResponse addTag(RequestTag tag, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.TAGS_POST, null, FastJsonUtils.toJSONNoFeatures(tag), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the tag with the specified ID
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse deleteTag(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TAGS_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

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
    ItemDataListWrapper getItems(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.TAGS_ID_ITEMS, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all releases in the project with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TagDataWrapper getTag(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TagDataWrapper) get(HttpUrls.TAGS_ID, pathParameters, queryParameters, TagDataWrapper.class, poolUtils, headers);
    }

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
    TagDataListWrapper getTags(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TagDataListWrapper) get(HttpUrls.TAGS, null, queryParameters, TagDataListWrapper.class, poolUtils, headers);
    }

    /**
     * PUT: Update the tag with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    AbstractRestResponse updateTag(RequestTag tag, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) post(HttpUrls.TAGS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(tag), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
