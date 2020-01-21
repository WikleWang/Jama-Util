package com.amt.jama.core.po.baselines;

import lombok.Data;

import java.util.List;

@Data
public class BaselineParent {
    /**
     * Item ID and version number for an item
     */
    private List<Integer> item;

    private Integer project;
}
