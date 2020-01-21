package com.amt.jama.core.po.filters;

import lombok.Data;

import java.util.List;

@Data
public class FilterRule {
    private FilterField field;
    private String operator;
    private List<String> values;
    private Integer itemType;
    private FilterQuery subQuery;
    private List<FilterRule> rules;
}
