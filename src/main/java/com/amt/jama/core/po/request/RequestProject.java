package com.amt.jama.core.po.request;

import lombok.Data;

import java.util.Map;

@Data
public class RequestProject {

    /**
     * Not Required if isFolder is true
     */
    private String projectKey;

    private Boolean isFolder;

    private Integer parent;

    /**
     * A map of field names to field values e.g.
     * {\"name\":\"Sample Project\", \"status\": 292, \"release\": 2, \"projectManager\": 23}
     */
    private Map<String,Object> fields;
}
