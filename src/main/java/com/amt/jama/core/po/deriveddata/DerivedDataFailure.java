package com.amt.jama.core.po.deriveddata;

import lombok.Data;

@Data
public class DerivedDataFailure {
    private Integer itemId;
    private Object databaseValue;
    private Object calculatedValue;
}
