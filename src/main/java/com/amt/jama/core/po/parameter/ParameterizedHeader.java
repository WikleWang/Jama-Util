package com.amt.jama.core.po.parameter;

import lombok.Data;

import java.util.Map;

@Data
public class ParameterizedHeader {

    private String value;
    private Map<String,String> parameters;
}
