package com.amt.jama.core.po.mediatype;

import lombok.Data;

import java.util.Map;

@Data
public class MediaType {
    private String type;
    private String subtype;
    private Map<String,String> parameters;
    private Boolean wildcardType;
    private Boolean wildcardSubtype;
}
