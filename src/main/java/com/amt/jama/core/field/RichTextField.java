package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.JamaFieldValue;
import com.amt.jama.core.fieldvalue.RichTextFieldValue;

/**
 * @author WangWei
 */
public class RichTextField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        RichTextFieldValue value = new RichTextFieldValue();
        populateFieldValue(value);
        return value;
    }

    public RichTextField(String type) {
        super(type);
    }

    public RichTextField() {
    }
}
