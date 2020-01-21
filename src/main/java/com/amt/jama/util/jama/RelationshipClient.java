package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.relationships.RelationshipDataListWrapper;
import com.amt.jama.core.po.relationships.RelationshipDataWrapper;
import com.amt.jama.core.po.relationshiptypes.RelationshipTypeDataListWrapper;
import com.amt.jama.core.po.relationshiptypes.RelationshipTypeDataWrapper;
import com.amt.jama.core.po.request.RequestRelationship;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class RelationshipClient extends BaseClient {

    RelationshipClient(String restVersion) {
        super(restVersion);
    }

    /**
     * DELETE: Remove an existing suspect link for the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse removeSuspectLink(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.RELATIONSHIPS_ID_SUSPECT_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new relationship
     * Body:RequestRelationship
     *
     * @return CreatedResponse
     */
    CreatedResponse createRelationship(RequestRelationship requestRelationship, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.RELATIONSHIPS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(requestRelationship), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse deleteRelationship(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.RELATIONSHIPS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return RelationshipDataWrapper
     */
    RelationshipDataWrapper getRelationship(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipDataWrapper) get(HttpUrls.RELATIONSHIPS_ID, pathParameters, queryParameters, RelationshipDataWrapper.class, poolUtils, headers);
    }

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
    RelationshipDataListWrapper getRelationships(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipDataListWrapper) get(HttpUrls.RELATIONSHIPS, null, queryParameters, RelationshipDataListWrapper.class, poolUtils, headers);
    }

    /**
     * PUT: Update the relationship with the specified ID
     * Path parameters:
     * 1. relationshipId (required)
     * Body: RequestRelationship
     */
    AbstractRestResponse updateRelationship(RequestRelationship requestRelationship, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.RELATIONSHIPS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(requestRelationship), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get the relationship type with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. timestamp (optional)
     * Get relationship type at this date and time. Requires ISO8601 formatting (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 2. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return RelationshipTypeDataWrapper
     */
    RelationshipTypeDataWrapper getRelationshipType(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipTypeDataWrapper) get(HttpUrls.RELATIONSHIPS_TYPES_ID, pathParameters, queryParameters, RelationshipTypeDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all relationship types
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return RelationshipTypeDataListWrapper
     */
    RelationshipTypeDataListWrapper getRelationshipTypes(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (RelationshipTypeDataListWrapper) get(HttpUrls.RELATIONSHIPS_TYPES, null, queryParameters, RelationshipTypeDataListWrapper.class, poolUtils, headers);
    }
}
