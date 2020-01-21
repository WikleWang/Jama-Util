package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;

public class TextBoxFieldValue extends JamaFieldValue{

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
    public void setValue(Object value) throws RestClientException {
        checkType(String.class, value);
        this.value = (String)value;
    }
}
