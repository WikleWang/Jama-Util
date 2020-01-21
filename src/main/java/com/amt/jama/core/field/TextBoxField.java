package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.JamaFieldValue;
import com.amt.jama.core.fieldvalue.TextBoxFieldValue;

public class TextBoxField extends JamaField{

    @Override
    public JamaFieldValue getValue() {
        TextBoxFieldValue value = new TextBoxFieldValue();
        populateFieldValue(value);
        return value;
    }

    public TextBoxField(String type) {
        super(type);
    }

    public TextBoxField() {
    }
}
