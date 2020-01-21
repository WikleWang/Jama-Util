package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.TestCaseStepsFieldValue;

public class TestCaseStepsField extends JamaField
{
    @Override
    public TestCaseStepsFieldValue getValue() {
        TestCaseStepsFieldValue value = new TestCaseStepsFieldValue();
        populateFieldValue(value);
        return value;
    }

    public TestCaseStepsField(String type) {
        super(type);
    }

    public TestCaseStepsField() {
        super();
    }
}
