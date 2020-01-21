package com.amt.jama.core.po.picklistoptions;

import lombok.Data;

@Data
public class PickListOption {
    private Integer pickList;
    private Integer id;
    private String name;
    private String description;
    private String value;
    private Boolean _default;
    private Boolean active;
    private String color;
    private Integer sortOrder;

    public Boolean getDefault() {
        return this._default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
