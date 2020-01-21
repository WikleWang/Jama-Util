package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestTag {

    private String name;

    /**
     * Only required on tag creation (POST)
     */
    private Integer project;
}
