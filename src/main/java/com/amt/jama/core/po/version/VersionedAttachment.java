package com.amt.jama.core.po.version;

import com.amt.jama.core.po.allowedresource.AllowedResource;
import com.amt.jama.core.po.lock.Lock;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class VersionedAttachment {

    private Lock lock;

    private String fileName;

    private String mineType;

    private Integer fileSize;

    private Integer version;

    /**
     * Currently active version of the versioned item.
     * If no version is active, currentVersion will not be returned.
     */
    private Integer currentVersion;

    private Integer id;

    private String documentKey;

    private String globalId;

    private Integer project;

    private Integer itemType;

    private Date createdDate;

    private Date modifiedDate;

    private Date lastActivityDate;

    private Integer createdBy;

    private Integer modifiedBy;

    /**
     * A set of resources and allowed permissions
     */
    private Map<String, AllowedResource> resources;

    /**
     * A map of field names to field values e.g.
     * {\"name\":\"Sample Item\", \"status\": 292, \"release\": 2, \"assigned\": 23}
     */
    private Map<String,Object> fields;
}
