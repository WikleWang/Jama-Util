package com.amt.jama.core.field;

import com.amt.jama.connect.constants.HttpConstants;
import com.amt.jama.core.fieldvalue.JamaFieldValue;
import com.amt.jama.core.po.itemtypes.ItemTypeField;
import com.amt.jama.util.jama.JamaInstance;

/**
 * @author wikle.wang
 */
public abstract class JamaField {
    private JamaInstance jamaInstance;
    private Integer id;
    private String name;
    private String label;
    private Boolean readOnly;
    private Boolean required;
    private Boolean triggerSuspect;
    private Boolean synchronize;
    private final String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.contains(HttpConstants.$) ? name.substring(0, name.indexOf(HttpConstants.$)) : name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getTriggerSuspect() {
        return triggerSuspect;
    }

    public void setTriggerSuspect(Boolean triggerSuspect) {
        this.triggerSuspect = triggerSuspect;
    }

    public Boolean getSynchronize() {
        return synchronize;
    }

    public void setSynchronize(Boolean synchronize) {
        this.synchronize = synchronize;
    }

    public String getType() {return type; }

    public void putFieldInto(ItemTypeField field) {
        this.setRequired(field.getRequired());
    }

    public abstract JamaFieldValue getValue();

    protected void populateFieldValue(JamaFieldValue fieldValue) {
        fieldValue.setName(this.getName());
        fieldValue.setLabel(this.getLabel());
        fieldValue.setField(this);
    }

    public void setJamaInstance(JamaInstance jamaInstance) {
        this.jamaInstance = jamaInstance;
    }


    public JamaInstance getJamaInstance() {
        return jamaInstance;
    }

    public JamaField(String type) {
        this.type = type;
    }

    public JamaField() {
        this.type = null;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
