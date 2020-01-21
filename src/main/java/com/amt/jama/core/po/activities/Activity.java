package com.amt.jama.core.po.activities;
import lombok.Data;

import java.util.Date;

@Data
public class Activity {

    private Integer id;

    private Date date;

    private String details;

    private String action;

    /**
     * ID of a user
     */
    private Integer user;

    private String userComment;

    /**
     * ID of an item
     */
    private Integer item;

    /**
     * ID of an item type
     */
    private Integer itemType;

    private String eventType;

    private String objectType;
}
