package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.constants.HttpConstants;
import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;
import com.amt.jama.core.po.picklistoptions.PickListOption;
import com.amt.jama.util.jama.JamaInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangWei
 */
public class MultiSelectFieldValue extends JamaFieldValue {

    private List<PickListOption> value = new ArrayList<>();

    public MultiSelectFieldValue(){};
    public MultiSelectFieldValue(JamaInstance jamaInstance) {
        setJamaInstance(jamaInstance);
    }

    @Override
    public List<PickListOption> getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if (value == null || HttpConstants.EMPTY_LIST.equals(value)) {
            return;
        }
        String[] values = value.substring(1, value.length() - 1).split(HttpConstants.COMMA);
        for(String option : values) {
            int optionId = Integer.parseInt(option.trim());
            // TODO
            try {
                this.value.add(getJamaInstance().getPickListOption(optionId));
            } catch (Exception e) {
                throw new RestClientException(e);
            }
        }
    }


    @Override
    public void setValue(Object value) throws TypeMismatchException {
        Class[] allowedTypes = {
                List.class,
                PickListOption.class
        };
        checkTypes(allowedTypes, value);
        if(value instanceof PickListOption) {
            this.value.add((PickListOption)value);
            return;
        }
        List<?> list = (List)value;
        List<PickListOption> options = new ArrayList<>();
        for(Object o : list) {
            checkType(PickListOption.class, o);
            options.add((PickListOption)o);
        }
        this.value = options;
    }

    public void setValue(List<PickListOption> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
