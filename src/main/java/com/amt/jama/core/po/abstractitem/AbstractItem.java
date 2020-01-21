package com.amt.jama.core.po.abstractitem;

import com.amt.jama.core.fieldvalue.JamaFieldValue;
import com.amt.jama.core.po.allowedresource.AllowedResource;
import com.amt.jama.core.po.users.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wikle.wang
 */
@Data
public class AbstractItem {

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

    private Date createdDate;

    private Date modifiedDate;

    private Date lastActivityDate;

    /**
     * ID of a user
     */
    private Integer createdBy;

    private User createdUser;

    /**
     * ID of a user
     */
    private Integer modifiedBy;

    private User modifiedUser;

    /**
     * A set of resources and allowed permissions
     */
    private Map<String, AllowedResource> resources;

    /**
     * A map of field names to field values e.g.
     * {\"name\":\"Sample Item\", \"status\": 292, \"release\": 2, \"assigned\": 23}
     */
    private Map<String, Object> fields;

    private List<JamaFieldValue> fieldValues = new ArrayList<>();



}
