package com.amt.jama.core.po.syncstatus;

import com.amt.jama.core.po.collectionsummary.CollectionSummary;
import lombok.Data;

@Data
public class SyncStatus {
    private Boolean inSync;
    private CollectionSummary collectionSummary;
}
