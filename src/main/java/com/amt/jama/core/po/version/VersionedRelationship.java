package com.amt.jama.core.po.version;

import lombok.Data;

import java.util.List;

@Data
public class VersionedRelationship {

    private Integer id;

    private List<Integer> fromItem;

    private List<Integer> toItem;

    private Boolean suspect;

    private List<Object> relationshipType;
}
