package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestRelationship {

    private Integer formItem;

    private Integer toItem;

    /**
     * Relationships will be created with the default type when providing a null or invalid relationship type
     */
    private Integer relationshipType;
}
