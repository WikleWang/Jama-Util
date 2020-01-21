package com.amt.jama.core.po.version;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Version {

    private List<Integer> versionedItem;

    private String type;

    private Integer item;

    private Integer versionNumber;

    private String changeDetails;

    private String comment;

    private Date createdDate;

    private Integer createdBy;
}
