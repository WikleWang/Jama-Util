package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.core.field.PickListField;
import com.amt.jama.core.po.picklistoptions.PickListOption;
import com.amt.jama.util.jama.JamaInstance;
import com.amt.jama.util.string.CompareUtil;

import java.util.List;

/**
 * @author WangWei
 */
public class PickListFieldValue extends JamaFieldValue {

    private PickListOption value;


    public PickListFieldValue() {

    }

    public PickListFieldValue(JamaInstance jamaInstance) {
        this.setJamaInstance(jamaInstance);
    }

    @Override
    public PickListOption getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if (value == null) {
            this.value = null;
            return;
        }
        Integer optionId = Integer.parseInt(value);
        // TODO
        setValue(optionId);
    }

    public void setValue(Integer id) throws RestClientException {
        try {
            this.value = this.getJamaInstance().getPickListOption(id);
        } catch (Exception e) {
            throw new RestClientException(e);
        }
    }

    @Override
    public void setValue(Object value) throws RestClientException {
        Class[] allowedTypes = {
                PickListOption.class,
                String.class
        };
        checkTypes(allowedTypes, value);
        if (value instanceof String) {
            value = getOption((String) value);
        }
        this.value = (PickListOption) value;
    }

    public void setValue(PickListOption value) {
        this.value = value;
    }

    public List<PickListOption> getOptions() throws RestClientException {
        return ((PickListField) field).getPickList().getOptions();
    }

    public PickListOption getOption(String optionName) throws RestClientException {
        PickListOption found = null;
        for (PickListOption option : getOptions()) {
            if (CompareUtil.closeEnough(option.getName(), optionName)) {
                if (found != null) {
                    throw new RestClientException("More than one pickListOption closely matches the string: \"" + optionName + "\" in pickList \"" + ((PickListField) field).getPickList() + "\"");
                }
                found = option;
            }
        }
        if (found == null) {
            throw new RestClientException("No pickListOptions matched the string: \"" + optionName + "\" in pickList \"" + ((PickListField) field).getPickList() + "\"");
        }
        return found;
    }

    @Override
    public String toString() {
        return this.value != null ? this.value.toString() : "";
    }
}
