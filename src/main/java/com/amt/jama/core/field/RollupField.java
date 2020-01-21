package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.RollupFieldValue;

public class RollupField extends JamaField{
    @Override
    public RollupFieldValue getValue() {
        RollupFieldValue value = new RollupFieldValue();
        populateFieldValue(value);
        return value;
    }

    public RollupField(String type) {
        super(type);
    }

    public RollupField() {
    }
}
