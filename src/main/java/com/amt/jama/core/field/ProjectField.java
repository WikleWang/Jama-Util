package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.ProjectFieldValue;

/**
 * @author WangWei
 */
public class ProjectField extends JamaField{

    @Override
    public ProjectFieldValue getValue() {
        ProjectFieldValue value = new ProjectFieldValue();
        populateFieldValue(value);
        return value;
    }

    public ProjectField(String type) {
        super(type);
    }

    public ProjectField() {
    }
}
