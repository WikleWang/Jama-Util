package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.core.valuetype.RichText;

/**
 * @author WangWei
 */
public class RichTextFieldValue extends JamaFieldValue {

    private RichText value;

    @Override
    public RichText getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = new RichText();
        this.value.setValue(value);
    }

    @Override
    public void setValue(Object value) throws RestClientException {
        checkType(String.class, value);
        this.value.setValue((String) value);
    }
}
