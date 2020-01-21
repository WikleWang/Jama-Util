package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.comments.CommentDataListWrapper;
import com.amt.jama.core.po.comments.CommentDataWrapper;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.request.RequestComment;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

public class CommentClient extends BaseClient {


    CommentClient(String restVersion) {
        super(restVersion);
    }

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
     *
     * @return CommentDataListWrapper
     */
    CommentDataListWrapper getComments(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CommentDataListWrapper) get(HttpUrls.COMMENTS, null, queryParameters, CommentDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all comments viewable by the current user
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return CommentDataWrapper
     */
    CommentDataWrapper getComment(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CommentDataWrapper) get(HttpUrls.COMMENTS_ID, pathParameters, queryParameters, CommentDataWrapper.class, poolUtils, headers);
    }

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
     *
     * @return CommentDataListWrapper
     */
    CommentDataListWrapper getCommentReplies(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CommentDataListWrapper) get(HttpUrls.COMMENTS_ID_REPLIES, pathParameters, queryParameters, CommentDataListWrapper.class, poolUtils, headers);
    }

    /**
     * POST: Create a new comment
     * Body: RequestComment
     *
     * @return CreatedResponse
     */
    CreatedResponse addComment(RequestComment requestComment, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.COMMENTS_POST, null, FastJsonUtils.toJSONNoFeatures(requestComment), null, CreatedResponse.class, poolUtils, headers);
    }
}
