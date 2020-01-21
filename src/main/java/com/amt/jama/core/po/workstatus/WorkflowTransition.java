package com.amt.jama.core.po.workstatus;

import lombok.Data;

@Data
public class WorkflowTransition {

    private String id;

    private String action;

    /**
     * ID of a pick list option
     */
    private Integer newStatus;
}
