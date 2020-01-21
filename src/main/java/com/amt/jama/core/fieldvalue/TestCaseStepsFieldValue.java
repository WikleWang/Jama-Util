package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.core.valuetype.TestCaseStep;

import java.util.ArrayList;
import java.util.List;

public class TestCaseStepsFieldValue extends JamaFieldValue{
    List<TestCaseStep> value = new ArrayList<>();

    @Override
    public List<TestCaseStep> getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        throw new RestClientException("testCaseSteps must call setValue(List<TestCaseStep> steps)");
    }

    @Override
    public void setValue(Object value) throws RestClientException {
        checkType(List.class, value);
        List<?> list = (List)value;
        List<TestCaseStep> testCaseSteps = new ArrayList<>();
        for(Object o : list) {
            checkType(TestCaseStep.class, o);
            testCaseSteps.add((TestCaseStep) o);
        }
        this.value = testCaseSteps;
    }


    public void setValue(List<TestCaseStep> value) {
        this.value = value;
    }
}
