package com.amt.jama.core.po.request;

import lombok.Data;

import java.util.Map;

@Data
public class RequestTestRun {

    /**
     * A map of field names to field values e.g.
     * {\"name\":\"Sample Item\", \"status\": 292, \"release\": 2, \"assigned\": 23}
     */
    private Map<String,Object> fields;
}
