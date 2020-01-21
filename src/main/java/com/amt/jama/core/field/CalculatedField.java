package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.CalculatedFieldValue;

/**
 * Jama自动计算的数据
 * @author WangWei
 */
public class CalculatedField extends JamaField{

    @Override
    public CalculatedFieldValue getValue() {
        CalculatedFieldValue value = new CalculatedFieldValue();
        super.populateFieldValue(value);
        return value;
    }

    public CalculatedField(String type) {
        super(type);
    }

    public CalculatedField() {super();}




}
