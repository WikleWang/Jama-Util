package com.amt.jama.core.po.wrapper;

import com.amt.jama.core.po.link.Link;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DataListWrapper<T> {
    private List<T> data;

    private Map<String, Link> links;

    private Map<String, Map<String, Object>> linked;

    private MetaListWrapper meta;
}

