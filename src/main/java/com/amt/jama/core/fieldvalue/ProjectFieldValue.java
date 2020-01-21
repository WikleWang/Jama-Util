package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;
import com.amt.jama.core.po.projects.Project;

/**
 * @author WangWei
 */
public class ProjectFieldValue extends JamaFieldValue {

    private Project value;

    @Override
    public Project getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if (value != null) {
            Integer projectId = Integer.parseInt(value);
            // TODO
        }
    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        checkType(Project.class, value);
        this.value = (Project) value;
    }
}
