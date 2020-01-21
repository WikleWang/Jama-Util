package com.amt.jama.core.po.projects;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Project {

    private Integer id;
    private String projectKey;
    private Integer parent;
    private Boolean isFolder;
    private Date createdDate;
    private Date modifiedDate;
    private Integer createdBy;
    private Integer modifiedBy;
    private Map<String,Object> fields;
}
