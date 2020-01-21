package com.amt.jama.core.po.filters;

import lombok.Data;

import java.util.List;

@Data
public class FilterQuery {
    private String name;
    private FilterRule rule;
    private List<FilterOrderRule> orderRules;
}
