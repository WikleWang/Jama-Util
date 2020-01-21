package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.corssorigin.CrossOriginDomainWhiteList;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class SystemClient extends BaseClient {

    SystemClient(String restVersion) {
        super(restVersion);
    }

    /**
     * GET: Get the current CORS domain whitelist
     *
     * @return CrossOriginDomainWhiteList
     */
    CrossOriginDomainWhiteList getDomains(HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CrossOriginDomainWhiteList) get(HttpUrls.SYSTEM_SETTING_CORS_DOMAINS, null, null, CrossOriginDomainWhiteList.class, poolUtils, headers);
    }

    /**
     * POST: Update CORS domain whitelist
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse setDomains(CrossOriginDomainWhiteList domainWhiteList, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) post(HttpUrls.SYSTEM_SETTING_CORS_DOMAINS_POST, null, FastJsonUtils.toJSONNoFeatures(domainWhiteList), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
