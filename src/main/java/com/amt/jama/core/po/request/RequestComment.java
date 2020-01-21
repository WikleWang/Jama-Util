package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestComment {

    private Integer inReplyTo;

    private RequestCommentBody body;

    private String commentType;

    private RequestCommentLocation location;
}
