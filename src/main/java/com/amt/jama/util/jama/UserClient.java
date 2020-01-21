package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.filters.FilterDataListWrapper;
import com.amt.jama.core.po.request.RequestActiveStatus;
import com.amt.jama.core.po.request.RequestUser;
import com.amt.jama.core.po.users.UserDataListWrapper;
import com.amt.jama.core.po.users.UserDataWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class UserClient extends BaseClient {

    UserClient(String restVersion) {
        super(restVersion);
    }

    /**
     * POST: Create a new user
     * Body: RequestUser
     */
    CreatedResponse createUser(RequestUser user, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.USERS_POST, null, FastJsonUtils.toJSONNoFeatures(user), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * GET: Gets the current user
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    UserDataWrapper getCurrentUser(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (UserDataWrapper) get(HttpUrls.USERS_CURRENT, null, queryParameters, UserDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Gets the current user's favorite filters
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    FilterDataListWrapper getFavoriteFilters(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (FilterDataListWrapper) get(HttpUrls.USERS_CURRENT_FAVORITE_FILTERS, null, queryParameters, FilterDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the user with the specified ID
     * Path parameters:
     * 1. userId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    UserDataWrapper getUser(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (UserDataWrapper) get(HttpUrls.USERS_ID, pathParameters, queryParameters, UserDataWrapper.class, poolUtils, headers);
    }

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
    UserDataListWrapper getUsers(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (UserDataListWrapper) get(HttpUrls.USERS, null, queryParameters, UserDataListWrapper.class, poolUtils, headers);
    }

    /**
     * PUT: Update the user with the specified ID
     * Path parameters:
     * 1. userId (required)
     * Body: RequestUser
     */
    AbstractRestResponse updateUser(RequestUser user, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.USERS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(user), null, AbstractRestResponse.class, poolUtils, headers);
    }


    /**
     * PUT: Update the active status for the user with the specified ID
     * Path parameters:
     * 1. userId (required)
     * Body: RequestActiveStatus
     */
    AbstractRestResponse setActiveStatus(RequestActiveStatus activeStatus, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.USERS_ID_ACTIVE_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(activeStatus), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
