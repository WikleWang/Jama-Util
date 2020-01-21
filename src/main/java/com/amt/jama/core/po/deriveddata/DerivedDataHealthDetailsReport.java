package com.amt.jama.core.po.deriveddata;

import lombok.Data;

import java.util.List;

@Data
public class DerivedDataHealthDetailsReport {
    private Integer numberItemsChecked;
    private Integer numberChecksFailed;
    private List<DerivedDataFailure> failures;
}
