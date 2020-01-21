package com.amt.jama.core.po.testruns;

import com.amt.jama.core.po.abstractitem.AbstractItem;
import lombok.Data;

import java.util.List;

@Data
public class TestRun extends AbstractItem {



    /**
     * The version of the test case at the time of test run creation
     */
    private Integer testCaseVersionNumber;

    /**
     * The current version of the test case that the test run is based on
     */
    private Integer testCaseCurrentVersionNumber;

    /**
     * The sort order within the test group at the time of test cycle creation
     */
    private Integer sortOrderFromTestGroup;

    /**
     * ID of a test cycle and ID of a test group
     */
    private List<Integer> testGroup;

}
