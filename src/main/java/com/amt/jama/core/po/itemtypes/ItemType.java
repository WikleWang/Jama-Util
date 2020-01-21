package com.amt.jama.core.po.itemtypes;

import lombok.Data;

import java.util.List;

@Data
public class ItemType {
    private Integer id;
    private String typeKey;
    private String display;
    private String displayPlural;
    private String description;
    private String image;
    private String category;
    private List<ItemTypeWidget> widgets;
    private List<ItemTypeField> fields;
    private Boolean system;

}
