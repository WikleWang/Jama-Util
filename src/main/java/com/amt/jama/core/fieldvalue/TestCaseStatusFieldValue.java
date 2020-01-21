package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;

public class TestCaseStatusFieldValue extends JamaFieldValue{

    private String value;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = value;
    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        throw new TypeMismatchException("Test Case status is not editable for field " + getName());
    }
}
