package com.amt.jama.core.po.content;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ContentDisposition {

    private String type;

    private Map<String,String> parameters;

    private String fileName;

    private Date creationDate;

    private Date modificationDate;

    private Date readDate;

    private Long size;

}
