package com.amt.jama.core.po.filters;

import lombok.Data;

@Data
public class Filter {
    private Integer id;
    private String name;
    private Integer author;
    private String projectScope;
    private Integer specifiedProject;
    private FilterQuery filterQuery;
    private Boolean _public;

    public Boolean getPublic() {
        return this._public;
    }

    public void setPublic(Boolean _public) {
        this._public = _public;
    }

}
