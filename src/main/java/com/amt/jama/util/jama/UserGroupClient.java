package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.request.RequestGroupUser;
import com.amt.jama.core.po.request.RequestUserGroup;
import com.amt.jama.core.po.usergroups.UserGroupDataListWrapper;
import com.amt.jama.core.po.usergroups.UserGroupDataWrapper;
import com.amt.jama.core.po.users.UserDataListWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class UserGroupClient extends BaseClient {

    UserGroupClient(String restVersion) {
        super(restVersion);
    }

    /**
     * GET: Create a new user group
     * Body: RequestUserGroup
     */
    CreatedResponse createUserGroup(RequestUserGroup userGroup, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.USER_GROUPS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(userGroup), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    CreatedResponse addUser(RequestGroupUser groupUser, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.USER_GROUPS_ID_USERS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(groupUser), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Delete the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     */
    AbstractRestResponse deleteUserGroup(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.USER_GROUPS_ID_DELETE, pathParameters, UserGroupDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    UserGroupDataWrapper getUserGroup(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (UserGroupDataWrapper) get(HttpUrls.USER_GROUPS_ID, pathParameters, queryParameters, UserGroupDataWrapper.class, poolUtils, headers);
    }

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
    UserDataListWrapper getUsers(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (UserDataListWrapper) get(HttpUrls.USER_GROUPS_ID_USERS, pathParameters, queryParameters, UserDataListWrapper.class, poolUtils, headers);
    }

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
    UserGroupDataListWrapper getUserGroups(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (UserGroupDataListWrapper) get(HttpUrls.USER_GROUPS, null, queryParameters, UserGroupDataListWrapper.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an existing user from the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. userId (required)
     */
    AbstractRestResponse removeUser(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.USER_GROUPS_ID_USERS_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the user group with the specified ID
     * Path parameters:
     * 1. id (required)
     * Body: RequestUserGroup
     */
    AbstractRestResponse updateUserGroup(RequestUserGroup userGroup, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.USER_GROUPS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(userGroup), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
