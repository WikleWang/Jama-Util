package com.amt.jama.core.po.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestItemType {

    private String typeKey;

    private String display;

    private String displayPlural;

    private String description;

    private String images;

    /**
     * Used to set the Category of the Item Type if it has special behavior.
     */
    private String category;

    private List<RequestItemTypeWidget> widgets;

}
