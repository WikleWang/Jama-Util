package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;

public class TimeFieldValue extends JamaFieldValue{

    private String value;

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        // todo: something better
        this.value = value;
    }

    @Override
    public void setValue(Object value) throws RestClientException {
        checkType(String.class, value);
        this.value = (String)value;
    }
}
