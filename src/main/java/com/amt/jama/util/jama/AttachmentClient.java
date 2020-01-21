package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.attachments.AttachmentDataWrapper;
import com.amt.jama.core.po.comments.CommentDataListWrapper;
import com.amt.jama.core.po.formdata.FormDataMultiPart;
import com.amt.jama.core.po.lock.LockDataWrapper;
import com.amt.jama.core.po.request.RequestLock;
import com.amt.jama.core.po.version.VersionDataListWrapper;
import com.amt.jama.core.po.version.VersionDataWrapper;
import com.amt.jama.core.po.version.VersionedAttachmentDataWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

public class AttachmentClient extends BaseClient {

    AttachmentClient(String restVersion) {
        super(restVersion);
    }

    void downloadAttachment(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        get(HttpUrls.ATTACHMENTS_ID_FILE, pathParameters, null, null, poolUtils, headers);
    }

    AttachmentDataWrapper getAttachment(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AttachmentDataWrapper) get(HttpUrls.ATTACHMENTS_ID, pathParameters, queryParameters, AttachmentDataWrapper.class, poolUtils, headers);
    }

    CommentDataListWrapper geComments(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CommentDataListWrapper) get(HttpUrls.ATTACHMENTS_ID_COMMENTS, pathParameters, queryParameters, CommentDataListWrapper.class, poolUtils, headers);
    }

    LockDataWrapper getLock(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (LockDataWrapper) get(HttpUrls.ATTACHMENTS_ID_LOCK, pathParameters, queryParameters, LockDataWrapper.class, poolUtils, headers);
    }

    VersionDataWrapper getVersion(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataWrapper) get(HttpUrls.ATTACHMENTS_ID_VERSIONS_NUM, pathParameters, queryParameters, VersionDataWrapper.class, poolUtils, headers);
    }

    VersionedAttachmentDataWrapper getVersionItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionedAttachmentDataWrapper) get(HttpUrls.ATTACHMENTS_ID_VERSIONS_NUM_ITEM, pathParameters, queryParameters, VersionedAttachmentDataWrapper.class, poolUtils, headers);
    }

    VersionDataListWrapper getVersions(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataListWrapper) get(HttpUrls.ATTACHMENTS_ID_VERSIONS, pathParameters, queryParameters, VersionDataListWrapper.class, poolUtils, headers);
    }

    AbstractRestResponse updateLock(RequestLock lock, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ATTACHMENTS_ID_LOCK_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(lock), null, AbstractRestResponse.class, poolUtils, headers);
    }

    AbstractRestResponse uploadFile(FormDataMultiPart formDataMultiPart, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.ATTACHMENTS_ID_FILE_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(formDataMultiPart), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
