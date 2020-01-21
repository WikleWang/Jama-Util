package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;

import java.util.Map;

public class FileClient extends BaseClient {

    FileClient(String restVersion) {
        super(restVersion);
    }

    /**
     * GET: Download attachment file from the attachment with the specified Jama URL
     * Query parameters:
     * 1. url (required)
     */
    void downloadFile(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        get(HttpUrls.FILES, null, queryParameters, null, poolUtils, headers);
    }

}
