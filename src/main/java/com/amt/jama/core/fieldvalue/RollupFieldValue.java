package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;

public class RollupFieldValue extends JamaFieldValue {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = value == null ? null : Integer.valueOf(value.substring(0, value.indexOf('%')));
    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        throw new TypeMismatchException("Rollup fields are not editable for field " + getName());
    }

    public void setValue(Integer value) {this.value = value;}
}
