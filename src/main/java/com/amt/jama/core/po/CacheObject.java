package com.amt.jama.core.po;

import com.alibaba.fastjson.JSONObject;

public class CacheObject {

    private Long lastFetch = null;

    private Integer timeout = 1000 * 60;

    private JSONObject value;

    public CacheObject(JSONObject value) {
        this.value = value;
        this.lastFetch = System.currentTimeMillis();
    }

    public CacheObject(Integer timeout, JSONObject value) {
        this.timeout = timeout;
        this.value = value;
    }

    public Long getLastFetch() {
        return lastFetch;
    }

    public void setLastFetch(Long lastFetch) {
        this.lastFetch = lastFetch;
    }


    public JSONObject getValue() {
        return value;
    }

    public void setValue(JSONObject value) {
        this.value = value;
    }


    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
