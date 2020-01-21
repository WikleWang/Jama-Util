package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;

/**
 * 计算字段的Value
 */
public class CalculatedFieldValue extends JamaFieldValue {

    private String value;

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = value;
    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        throw new TypeMismatchException("Calculated fields are not editable for field " + getName());
    }
}
