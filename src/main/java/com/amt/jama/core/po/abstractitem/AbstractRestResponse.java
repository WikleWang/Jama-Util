package com.amt.jama.core.po.abstractitem;

import com.amt.jama.core.po.pageinfo.PageInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AbstractRestResponse {

    private Integer status;

    private String statusReasonPhrase;

    private PageInfo pageInfo;

    private Map<String, List<String>> headers;

}
