package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestLocation {

    /**
     * This can point to either a project or a parent item at which this item is located, not both.
     */
    private RequestParent parent;
}
