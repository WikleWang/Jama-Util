package com.amt.jama.core.po.itemtypes;

import com.amt.jama.core.po.picklistoptions.PickListOption;
import lombok.Data;

import java.util.List;

@Data
public class ItemTypeField {
    private Integer id;
    private String name;
    private String label;
    private String fieldType;
    private Boolean readOnly;
    private Boolean readOnlyAllowApiOverwrite;
    private Boolean required;
    private Boolean triggerSuspect;
    private Boolean synchronize;
    private Integer pickList;
    private String textType;
    private Integer itemType;

    private List<PickListOption> pickListOptions;
}
