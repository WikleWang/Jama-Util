package com.amt.jama.core.po.deriveddata;

import lombok.Data;

import java.util.Date;

@Data
public class DerivedDataHealthReport {
    private String reportType;
    private Date generatedDate;
    private DerivedDataHealthDetailsReport derivedDataHealthDetails;

}
