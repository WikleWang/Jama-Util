package com.amt.jama.core.po.testruns;

import lombok.Data;

import java.util.List;

@Data
public class TestRunGenerationConfig {

    /**
     * The Test Group IDs of the Test Groups from which you would like to generate Test Runs.
     * Do not specify anything to include all groups.
     */
    private List<Integer> testGroupsToInclude;

    /**
     * Only valid after generating the first Test Cycle, you may choose to only generate Test Runs that were a specified status in the previous cycle.
     * Do not specify anything to include all statuses
     */
    private List<String> testRunStatusesToInclude;

}