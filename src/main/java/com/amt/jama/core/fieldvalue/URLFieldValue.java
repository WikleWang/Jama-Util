package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;

public class URLFieldValue extends JamaFieldValue {
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) throws RestClientException {
        this.value = value;
    }


    public void setValue(Object value) throws TypeMismatchException {
        checkType(String.class, value);
        this.value = (String)value;
    }
}
