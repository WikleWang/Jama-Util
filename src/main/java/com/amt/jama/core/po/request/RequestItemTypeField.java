package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestItemTypeField {

    private String name;

    private String label;

    private String fieldType;

    private Boolean readOnly;

    private Boolean readOnlyAllowApiOverwrite;

    private Boolean required;

    private Boolean triggerSuspect;

    private Boolean synchronize;

    private Integer pickList;

    private String textType;
}
