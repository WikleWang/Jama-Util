package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;
import com.amt.jama.core.field.JamaField;
import com.amt.jama.util.jama.JamaInstance;

/**
 * @author wikle.wang
 */
public abstract class JamaFieldValue {

    private JamaInstance jamaInstance;

    private String name;

    private String label;

    protected JamaField field;

    public abstract Object getValue();

    public abstract void setValue(String value) throws RestClientException;

    public abstract void setValue(Object value) throws RestClientException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public void setField(JamaField field) {
        this.field = field;
        this.name = field.getName();
        this.label = field.getLabel();
    }

    protected void checkType(Class clazz, Object value) throws TypeMismatchException {
        if (!clazz.isInstance(value)) {
            throw new TypeMismatchException("Expected type " + clazz.getName() + ", received " + value.getClass() + " instead. " +
                    "In field: " + getName());
        }
    }

    protected void checkTypes(Class[] classes, Object value) throws TypeMismatchException {
        for (Class clazz : classes) {
            if (clazz.isInstance(value)) {
                return;
            }
        }
        StringBuilder message = new StringBuilder("Expected one type of ");
        for (int i = 0; i < classes.length; ++i) {
            message.append(classes[i].getName()).append(i == classes.length - 1 ? "" : ", ");
        }
        throw new TypeMismatchException(message + ". Received " + value.getClass() + " instead. In field: " + getName());
    }

    public void setJamaInstance(JamaInstance jamaInstance) {
        this.jamaInstance = jamaInstance;
    }

    public JamaInstance getJamaInstance() {
        return jamaInstance;
    }

    @Override
    public String toString() {
        return getValue() == null ? "" : getValue().toString();
    }

}
