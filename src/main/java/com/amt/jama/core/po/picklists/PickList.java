package com.amt.jama.core.po.picklists;

import com.amt.jama.core.po.picklistoptions.PickListOption;
import lombok.Data;

import java.util.List;

@Data
public class PickList {
    private Integer id;
    private String name;
    private String description;
    private List<PickListOption> options;
}
