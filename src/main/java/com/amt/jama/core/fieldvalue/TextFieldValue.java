package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;

public class TextFieldValue extends JamaFieldValue {

    private String value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = value;
    }

    @Override
    public void setValue(Object value) throws RestClientException {
        checkType(String.class, value);
        this.value = (String) value;
    }
}
