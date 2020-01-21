package com.amt.jama.core.field;

import com.amt.jama.core.fieldvalue.PickListFieldValue;
import com.amt.jama.core.po.picklistoptions.PickListOption;
import com.amt.jama.core.po.picklists.PickList;

import java.util.List;

/**
 * @author WangWei
 */
public class PickListField extends JamaField {

    private Integer pickListId;

    private PickList pickList = new PickList();

    @Override
    public PickListFieldValue getValue() {
        PickListFieldValue value = new PickListFieldValue();
        populateFieldValue(value);
        return value;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    public PickList getPickList() {
        return pickList;
    }

    public PickListField(String type) {
        super(type);
    }

    public  PickListField(String type, Integer pickListId, List<PickListOption> options) throws Exception {
        super(type);
        this.pickListId = pickListId;
        this.pickList.setId(pickListId);
        this.pickList.setOptions(options);
    }

    public PickListField() {
        super();
    }

}
