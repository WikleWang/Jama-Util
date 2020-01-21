package com.amt.jama.core.po.lock;

import lombok.Data;

import java.util.Date;

@Data
public class Lock {
    private Boolean locked;
    private Date lastLockedDate;
    private Integer lockedBy;
}
