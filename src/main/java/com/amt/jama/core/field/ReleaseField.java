package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.ReleaseFieldValue;

/**
 * @author WangWei
 */
public class ReleaseField extends JamaField {
    @Override
    public ReleaseFieldValue getValue() {
        ReleaseFieldValue value = new ReleaseFieldValue();
        populateFieldValue(value);
        return value;
    }

    public ReleaseField(String type) {
        super(type);
    }

    public ReleaseField() {
    }
}
