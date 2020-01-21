package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.TestCaseStatusFieldValue;

public class TestCaseStatusField extends JamaField
{
    @Override
    public TestCaseStatusFieldValue getValue() {
        TestCaseStatusFieldValue value = new TestCaseStatusFieldValue();
        populateFieldValue(value);
        return value;
    }

    public TestCaseStatusField(String type) {
        super(type);
    }

    public TestCaseStatusField() {
    }
}
