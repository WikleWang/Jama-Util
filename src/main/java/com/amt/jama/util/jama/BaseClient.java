package com.amt.jama.util.jama;

import com.alibaba.fastjson.JSONObject;
import com.amt.jama.connect.constants.HttpConstants;
import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.CacheObject;
import com.amt.jama.util.json.FastJsonUtils;
import com.amt.jama.util.redis.RedisUtil;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BaseClient {

    private String restVersion = "/rest/v1";

    private static Map<String, CacheObject> caches = new HashMap<>();

    private static Logger log = Logger.getLogger(BaseClient.class);

    BaseClient(String restVersion) {
        this.restVersion = restVersion;
        monitoring();
    }


    private void monitoring() {
        new Timer("cacheTimer").schedule(new TimerTask() {
            @Override
            public void run() {
                Map<String, CacheObject> cacheObjectMap = new HashMap<>();
                for (Map.Entry<String, CacheObject> entry : caches.entrySet()) {
                    String key = entry.getKey();
                    CacheObject value = entry.getValue();
                    Long lastFetch = value.getLastFetch();
                    Long now = System.currentTimeMillis();
                    if (now - lastFetch < value.getTimeout()) {
                        cacheObjectMap.put(key, value);
                    }
                }
                caches = cacheObjectMap;
            }
        }, 1000, 1000 );
    }

    /**
     * @param url             查询地址
     * @param pathParameters  pathParameters
     * @param queryParameters queryParameters
     * @param clz             clz
     * @param poolUtils       poolUtils
     * @param headers         headers
     * @return Object
     * @throws Exception 已抛出
     */
    Object get(String url, Map<String, String> pathParameters, Map<String, String> queryParameters, Class<?> clz, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        url = this.restVersion + HttpUrls.getUrl(url, pathParameters, queryParameters);
        JSONObject data = null;

        // find in this app
        if (caches.containsKey(url)) {
            data = caches.get(url).getValue();
        }

        // find in redis
        boolean active = RedisUtil.redisActive();
        if (data == null && active) {
            String redisData = RedisUtil.get(url);
            if (redisData != null) {
                log.info("get data in redis for url : " + url);
                data = JSONObject.parseObject(redisData);
            }
        }

        // if data is empty, search in jama and then set into redis
        if (data == null || data.isEmpty()) {
            data = this.getObjectByUrl(url, poolUtils, headers);
            if (data != null && active) {
                log.info("set data in redis for url : " + url);
                RedisUtil.set(url, data.toJSONString(), null);
            }
            if (data != null && !caches.containsKey(url)) {
                caches.put(url, new CacheObject(data));
            }
        }
        return clz != null && data != null ? FastJsonUtils.toBean(data.toJSONString(), clz) : null;
    }


    /**
     * @param url            查询地址
     * @param pathParameters pathParameters
     * @param data           更新的数据
     * @param entity         entity
     * @param clz            clz
     * @param poolUtils      poolUtils
     * @param headers        headers
     * @return Object
     * @throws Exception 已抛出
     */
    Object post(String url, Map<String, String> pathParameters, String data, HttpEntity entity, Class<?> clz, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        url = this.restVersion + HttpUrls.getUrl(url, pathParameters, null);
        String result = poolUtils.post(url, data, entity, HttpConstants.APPLICATION_JSON, headers);
        return clz != null && result != null ? FastJsonUtils.toBean(result, clz) : null;
    }

    /**
     * @param url            查询地址
     * @param pathParameters pathParameters
     * @param data           更新的数据
     * @param entity         entity
     * @param clz            clz
     * @param poolUtils      poolUtils
     * @param headers        headers
     * @return Object
     * @throws Exception 已抛出
     */
    Object put(String url, Map<String, String> pathParameters, String data, StringEntity entity, Class<?> clz, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        url = this.restVersion + HttpUrls.getUrl(url, pathParameters, null);
        // 此处还要判断是否为文件上传，是则MULTIPART_FORM_DATA，否则为APPLICATION_JSON
        String result = poolUtils.put(url, data, entity, (data.contains(HttpConstants.MEDIA_TYPE) && data.contains(HttpConstants.BODY_PARTS) ? HttpConstants.MULTIPART_FORM_DATA : HttpConstants.APPLICATION_JSON), headers);
        return clz != null && result != null ? FastJsonUtils.toBean(result, clz) : null;
    }

    /**
     * @param url            查询地址
     * @param pathParameters pathParameters
     * @param data           更新的数据
     * @param entity         entity
     * @param clz            clz
     * @param poolUtils      poolUtils
     * @param headers        headers
     * @return Object
     * @throws Exception 已抛出
     */
    Object patch(String url, Map<String, String> pathParameters, String data, StringEntity entity, Class<?> clz, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        url = this.restVersion + HttpUrls.getUrl(url, pathParameters, null);
        String result = poolUtils.patch(url, data, entity, HttpConstants.APPLICATION_JSON, headers);
        return clz != null && result != null ? FastJsonUtils.toBean(result, clz) : null;
    }

    /**
     * @param url            查询地址
     * @param pathParameters pathParameters
     * @param clz            clz
     * @param poolUtils      poolUtils
     * @param headers        headers
     * @return Object
     * @throws Exception 已抛出
     */
    Object delete(String url, Map<String, String> pathParameters, Class<?> clz, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        url = this.restVersion + HttpUrls.getUrl(url, pathParameters, null);
        String result = poolUtils.delete(url, HttpConstants.APPLICATION_JSON, headers);
        return clz != null && result != null ? FastJsonUtils.toBean(result, clz) : null;
    }

    /**
     * 通过URL获取JSONObject数据
     *
     * @param url       查询地址
     * @param poolUtils poolUtils
     * @param headers   headers
     * @return JSONObject
     * @throws Exception 已抛出
     */
    private JSONObject getObjectByUrl(String url, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        String data = getByUrl(url, poolUtils, headers);
        return data.isEmpty() ? null : JSONObject.parseObject(data);
    }

    /**
     * 通过URL获取数据
     *
     * @param url       查询地址
     * @param poolUtils poolUtils
     * @param headers   headers
     * @return String
     * @throws Exception 已抛出
     */
    private String getByUrl(String url, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return poolUtils.get(url, HttpConstants.APPLICATION_JSON, headers);
    }


}
