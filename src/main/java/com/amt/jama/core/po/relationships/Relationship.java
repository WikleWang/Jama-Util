package com.amt.jama.core.po.relationships;

import lombok.Data;

@Data
public class Relationship {
    private Integer id;
    private Integer formItem;
    private Integer toItem;
    private Integer relationshipType;
    private Boolean suspect;
}
