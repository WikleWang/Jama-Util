package com.amt.jama.core.po.items;

import com.amt.jama.core.po.abstractitem.AbstractItem;
import com.amt.jama.core.po.location.Location;
import com.amt.jama.core.po.lock.Lock;
import lombok.Data;

@Data
public class Item extends AbstractItem {

    private Integer childItemType;
    private Lock lock;
    private Location location;

    @Override
    public String toString() {
        return this.getFields().get("name").toString();
    }


}
