package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.releases.ReleaseDataListWrapper;
import com.amt.jama.core.po.releases.ReleaseDataWrapper;
import com.amt.jama.core.po.request.RequestRelease;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class ReleaseClient extends BaseClient {

   ReleaseClient(String restVersion) {
        super(restVersion);
    }



    /**
     * POST: Create a new release
     * Body: RequestRelease
     *
     * @return CreatedResponse
     */
    CreatedResponse createRelease(RequestRelease release, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.RELEASES_POST, null, FastJsonUtils.toJSONNoFeatures(release), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get all releases in the project with the specified ID
     * Path parameters:
     * 1. releaseId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ReleaseDataWrapper
     */
    ReleaseDataWrapper getRelease(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ReleaseDataWrapper) get(HttpUrls.RELEASES_ID, pathParameters, queryParameters, ReleaseDataWrapper.class, poolUtils, headers);
    }

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
    ReleaseDataListWrapper getReleases(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ReleaseDataListWrapper) get(HttpUrls.RELEASES, null, queryParameters, ReleaseDataListWrapper.class, poolUtils, headers);
    }

    /**
     * PUT: Update the release with the specified ID
     * Path parameters:
     * 1. releaseId (required)
     * Body: RequestRelease
     */
    AbstractRestResponse updateRelease(RequestRelease release, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.RELEASES_ID_PUT, null, FastJsonUtils.toJSONNoFeatures(release), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
