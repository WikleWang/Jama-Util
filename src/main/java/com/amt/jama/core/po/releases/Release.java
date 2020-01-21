package com.amt.jama.core.po.releases;

import lombok.Data;

import java.util.Date;

@Data
public class Release {

    private Integer id;
    private String name;
    private String description;
    private Integer project;
    private Date releaseDate;
    private Boolean active;
    private Boolean archived;
    private Integer itemCount;

    @Override
    public String toString() {
        return this.name;
    }
}
