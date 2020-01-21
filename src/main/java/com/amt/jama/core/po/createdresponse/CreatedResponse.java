package com.amt.jama.core.po.createdresponse;

import com.amt.jama.core.po.pageinfo.PageInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreatedResponse {
    private Integer status;
    private Integer statusReasonPhrase;
    private PageInfo pageInfo;
    private Map<String, List<String>> headers;
    private String location;
    private Integer id;
}
