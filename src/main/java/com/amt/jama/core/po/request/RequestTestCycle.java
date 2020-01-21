package com.amt.jama.core.po.request;

import com.amt.jama.core.po.testruns.TestRunGenerationConfig;
import lombok.Data;

import java.util.Map;

@Data
public class RequestTestCycle {

    /**
     * A map of field names to field values e.g.
     * {\"name\":\"Sample Item\", \"status\": 292, \"release\": 2, \"assigned\": 23}
     */
    private Map<String,Object> fields;

    /**
     * Settings for how test runs will be generated in this test cycle
     */
    private TestRunGenerationConfig testRunGenerationConfig;

}
