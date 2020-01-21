package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;

/**
 * @author WangWei
 */
public class IntegerFieldValue extends JamaFieldValue {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = value == null ? null : Integer.valueOf(value);
    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        checkType(Integer.class, value);
        this.value = (Integer) value;
    }

    public void setValue(Integer value) {this.value = value;}
}
