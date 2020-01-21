package com.amt.jama.core.po.request;

import com.amt.jama.core.po.location.Location;
import lombok.Data;

import java.util.Map;

@Data
public class RequestItem {

    private String globalId;
    private Integer project;
    private Integer itemType;
    private Integer childItemType;
    private Location location;
    private Map<String,Object> fields;
}
