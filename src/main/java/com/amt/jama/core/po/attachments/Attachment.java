package com.amt.jama.core.po.attachments;

import com.amt.jama.core.po.allowedresource.AllowedResource;
import com.amt.jama.core.po.lock.Lock;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Attachment {

    private Lock lock;

    private String fileName;

    private String mineType;

    private Integer fileSize;

    private Integer id;

    private String documentKey;

    private String globalId;

    /**
     * ID of a project
     */
    private Integer project;

    /**
     * ID of a item type
     */
    private Integer itemType;

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
    private Map<String, AllowedResource> resources;

    /**
     * A map of field names to field values e.g.
     * {\"name\":\"Sample Item\", \"status\": 292, \"release\": 2, \"assigned\": 23}
     */
    private Map<String, Object> fields;
}
