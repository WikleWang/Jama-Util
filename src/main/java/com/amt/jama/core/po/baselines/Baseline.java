package com.amt.jama.core.po.baselines;

import lombok.Data;

import java.util.Date;

@Data
public class Baseline {
    private Integer id;
    private String name;
    private String description;

    private Date createdDate;

    /**
     * ID of a user
     */
    private Integer createdBy;


    private Integer project;

    private BaselineOrigin origin;

    private String type;

}
