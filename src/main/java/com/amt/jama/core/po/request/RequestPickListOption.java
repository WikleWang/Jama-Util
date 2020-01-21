package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestPickListOption {

    private String description;

    private String name;

    private String value;

    private String color;

    private Integer sortOrder;

    private Boolean _default;

    public Boolean getDefault() {
        return this._default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }
}
