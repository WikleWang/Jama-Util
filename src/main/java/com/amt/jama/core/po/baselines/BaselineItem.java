package com.amt.jama.core.po.baselines;

import com.amt.jama.core.po.allowedresource.AllowedResource;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author wikle.wang
 */
@Data
public class BaselineItem {

    private Integer version;

    /**
     * Currently active version of the versioned item.
     * If no version is active, currentVersion will not be returned.
     */
    private Integer currentVersion;

    /**
     * ID of a baseline
     */
    private Integer baseline;

    private BaselineLocation baselineLocation;

    private String type;

    private Integer id;

    private String documentKey;

    private String globalId;

    /**
     * ID of a project
     */
    private Integer project;

    /**
     * ID of an item type
     */
    private Integer itemType;

    private Integer childItemType;

    private Date createdDate;

    private Date modifiedDate;

    private Date lastActivityDate;

    /**
     * ID of a user
     */
    private Integer createdBy;

    /**
     * ID of a user
     */
    private Integer modifiedBy;

    /**
     * A set of resources and allowed permissions
     */
    private Map<String,AllowedResource> resources;

    /**
     * A map of field names to field values e.g.
     * {\"name\":\"Sample Item\", \"status\": 292, \"release\": 2, \"assigned\": 23}
     */
    private Map<String,Object> fields;



}
