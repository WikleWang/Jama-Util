package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestPatchOperation {

    private String op;

    private String path;

    private Object value;
}
