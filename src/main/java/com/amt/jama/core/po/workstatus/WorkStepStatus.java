package com.amt.jama.core.po.workstatus;

import lombok.Data;

@Data
public class WorkStepStatus {

    private String stepName;

    private Integer completedWork;

    private Integer totalWork;
}
