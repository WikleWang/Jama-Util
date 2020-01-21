package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.FlagFieldValue;

/**
 * @author WangWei
 */
public class FlagField extends JamaField {
    @Override
    public FlagFieldValue getValue() {
        FlagFieldValue value = new FlagFieldValue();
        populateFieldValue(value);
        return value;
    }

    public FlagField(String type) {
        super(type);
    }

    public FlagField() {
    }
}
