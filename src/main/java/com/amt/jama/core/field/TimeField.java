package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.TimeFieldValue;

public class TimeField extends JamaField {
    @Override
    public TimeFieldValue getValue() {
        TimeFieldValue timeFieldValue = new TimeFieldValue();
        populateFieldValue(timeFieldValue);
        return timeFieldValue;
    }

    public TimeField(String type) {
        super(type);
    }

    public TimeField() {
        super();
    }
}
