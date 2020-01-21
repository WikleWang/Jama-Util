package com.amt.jama.core.po.wrapper;

import com.amt.jama.core.po.link.Link;
import lombok.Data;

import java.util.Map;

@Data
public class DataWrapper<T> {

    private T data;

    private Map<String, Link> links;

    private Map<String, Map<String, Object>> linked;

    private MetaWrapper meta;
}

