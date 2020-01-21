package com.amt.jama.core.field;


import com.amt.jama.core.fieldvalue.URLFieldValue;

public class URLField extends JamaField {
    @Override
    public URLFieldValue getValue() {
        URLFieldValue urlFieldValue = new URLFieldValue();
        populateFieldValue(urlFieldValue);
        return urlFieldValue;
    }

    public URLField(String type) {
        super(type);
    }

    public URLField() {
        super();
    }

}