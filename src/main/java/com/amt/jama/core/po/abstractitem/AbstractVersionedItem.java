package com.amt.jama.core.po.abstractitem;

import lombok.Data;

/**
 * @author wikle.wang
 */
@Data
public class AbstractVersionedItem {

    private Integer id;

    /**
     * Currently active version of the versioned item. If no version is active, currentVersion will not be returned.
     */
    private Integer currentVersion;

    private Integer version;

    private String type;
}
