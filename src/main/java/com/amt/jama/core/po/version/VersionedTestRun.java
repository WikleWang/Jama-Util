package com.amt.jama.core.po.version;

import com.amt.jama.core.po.allowedresource.AllowedResource;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class VersionedTestRun {

    /**
     * Currently active version of the versioned item.
     * If no version is active, currentVersion will not be returned.
     */
    private Integer version;

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
     * The version of the test case at the time of test run creation
     */
    private Integer testCaseVersionNumber;

    /**
     * The current version of the test case that the test run is based on
     */
    private Integer testCaseCurrentVersionNumber;

    /**
     * The sort order within the test group at the time of test cycle creation
     */
    private Integer sortOrderFromTestGroup;

    /**
     * ID of a test cycle and ID of a test group
     */
    private List<Integer> testGroup;

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
