package com.amt.jama.core.po.workstatus;

import lombok.Data;

import java.util.List;

@Data
public class WorkStatus {

    private String workKey;

    private String jobName;

    private Boolean inProgress;

    private List<WorkStepStatus> workSteps;

}
