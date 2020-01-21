package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestRelease {

    private String name;

    private String description;

    private String releaseDate;

    private Integer project;
}
