package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.IntegerFieldValue;

/**
 * @author WangWei
 */
public class IntegerField extends JamaField {
    @Override
    public IntegerFieldValue getValue() {
        IntegerFieldValue value = new IntegerFieldValue();
        populateFieldValue(value);
        return value;
    }

    public IntegerField(String type) {
        super(type);
    }

    public IntegerField() {
    }
}
