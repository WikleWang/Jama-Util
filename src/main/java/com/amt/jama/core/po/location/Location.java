package com.amt.jama.core.po.location;

import com.amt.jama.core.po.parent.Parent;
import lombok.Data;

@Data
public class Location {
    private Integer sortOrder;
    private Integer globalSortOrder;
    private String sequence;
    private Parent parent;
}
