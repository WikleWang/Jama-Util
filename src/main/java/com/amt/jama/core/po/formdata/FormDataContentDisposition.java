package com.amt.jama.core.po.formdata;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class FormDataContentDisposition {
    private String type;
    private Map<String, String> parameters;
    private String fileName;
    private Date creationDate;
    private Date modificationDate;
    private Date readDate;
    private Long size;
    private String name;


}
