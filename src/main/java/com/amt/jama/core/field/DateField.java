package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.DateFieldValue;

/**
 * @author WangWei
 */
public class DateField extends JamaField {

    @Override
    public DateFieldValue getValue() {
        DateFieldValue value = new DateFieldValue();
        populateFieldValue(value);
        return value;
    }

    public DateField(String type) {
        super(type);
    }

    public DateField() {
    }
}
