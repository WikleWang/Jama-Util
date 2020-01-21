package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;

/**
 * @author WangWei
 */
public class FlagFieldValue extends JamaFieldValue {

    private Boolean value;

    @Override
    public Boolean getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = value == null ? null : Boolean.valueOf(value);
    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        checkType(Boolean.class, value);
        this.value = (Boolean) value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
