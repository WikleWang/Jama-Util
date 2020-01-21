package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.TextFieldValue;

public class TextField extends JamaField {
    @Override
    public TextFieldValue getValue() {
        TextFieldValue value = new TextFieldValue();
        populateFieldValue(value);
        return value;
    }


    public TextField(String type) {
        super(type);
    }

    public TextField() {
        super();
    }
}
