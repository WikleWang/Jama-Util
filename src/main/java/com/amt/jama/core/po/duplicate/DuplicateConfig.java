package com.amt.jama.core.po.duplicate;

import lombok.Data;

@Data
public class DuplicateConfig {
    private Boolean includeTags;
    private Boolean includeAttachments;
    private Boolean includeLinks;
}
