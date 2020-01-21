package com.amt.jama.core.po.comments;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer id;

    /**
     * ID of a comment
     */
    private Integer inReplyTo;

    private Date createdDate;

    private Integer createdBy;
    private Integer modifiedBy;
    private Boolean deleted;

    private String status;

    private CommentBody body;

    private String commentType;

    private CommentLocation location;


}
