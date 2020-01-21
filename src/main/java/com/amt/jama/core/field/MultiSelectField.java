package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.MultiSelectFieldValue;
import com.amt.jama.core.po.picklistoptions.PickListOption;
import com.amt.jama.core.po.picklists.PickList;

import java.util.List;

/**
 * @author WangWei
 */
public class MultiSelectField extends JamaField {

    private Integer pickListId;

    private PickList pickList = new PickList();

    public PickList getPickList() {
        return this.pickList;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    @Override
    public MultiSelectFieldValue getValue() {
        MultiSelectFieldValue value = new MultiSelectFieldValue();
        populateFieldValue(value);
        return value;
    }

    public MultiSelectField(String type) {
        super(type);
    }

    public MultiSelectField(String type, Integer pickListId, List<PickListOption> options) {
        super(type);
        this.pickListId = pickListId;
        this.pickList.setId(pickListId);
        this.pickList.setOptions(options);
    }


    public MultiSelectField() {
        super();
    }
}
