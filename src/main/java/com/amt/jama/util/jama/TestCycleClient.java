package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.request.RequestPatchOperation;
import com.amt.jama.core.po.request.RequestTestCycle;
import com.amt.jama.core.po.testcycles.TestCycleDataWrapper;
import com.amt.jama.core.po.testcycles.TestCycleTestGroupDataWrapper;
import com.amt.jama.core.po.testruns.TestRunDataListWrapper;
import com.amt.jama.core.po.version.VersionDataListWrapper;
import com.amt.jama.core.po.version.VersionDataWrapper;
import com.amt.jama.core.po.version.VersionedTestCycleDataWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wikle.wang
 */
public class TestCycleClient extends BaseClient {

    TestCycleClient(String restVersion) {
        super(restVersion);
    }


    /**
     * DELETE: Delete the test cycle with the specified ID, including the test runs in the test cycle
     * Path parameters:
     * 1. testCycleId (required)
     */
    AbstractRestResponse deleteTestCycle(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.TEST_CYCLES_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get the test cycle with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TestCycleDataWrapper getTestCycle(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestCycleDataWrapper) get(HttpUrls.TEST_CYCLES_ID, pathParameters, queryParameters, TestCycleDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the test cycle test group for the test cycle with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * 2. testGroupId (required)
     * Get the test group with the specified ID
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    TestCycleTestGroupDataWrapper getTestGroup(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestCycleTestGroupDataWrapper) get(HttpUrls.TEST_CYCLES_ID_TEST_GROUP_ID, pathParameters, queryParameters, TestCycleTestGroupDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all test runs for the test cycle with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    TestRunDataListWrapper getTestRuns(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TestRunDataListWrapper) get(HttpUrls.TEST_CYCLES_ID_TEST_RUNS, pathParameters, queryParameters, TestRunDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionDataWrapper getVersion(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataWrapper) get(HttpUrls.TEST_CYCLES_ID_VERSIONS_NUM, pathParameters, queryParameters, TestRunDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the snapshot of the test cycle at the specified version
     * Path parameters:
     * 1. testCycleId (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionedTestCycleDataWrapper getVersionedItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionedTestCycleDataWrapper) get(HttpUrls.TEST_CYCLES_ID_VERSIONS_NUM_ITEM, pathParameters, queryParameters, VersionedTestCycleDataWrapper.class, poolUtils, headers);
    }
    /**
     * GET: Get all versions for the item with the specified ID
     * Path parameters:
     * 1. testCycleId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionDataListWrapper getVersions(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataListWrapper) get(HttpUrls.TEST_CYCLES_ID_VERSIONS, pathParameters, queryParameters, VersionDataListWrapper.class, poolUtils, headers);
    }
    /**
     * PATCH: Update the test cycle with the specified ID, including regenerating the test runs in the test cycle
     * Path parameters:
     * 1. testCycleId (required)
     * Body: array[RequestPatchOperation]
     */
    AbstractRestResponse patchTestCycle(List<RequestPatchOperation> operations, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) patch(HttpUrls.TEST_CYCLES_ID_PATCH, pathParameters, FastJsonUtils.toJSONNoFeatures(operations), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the test cycle with the specified ID, including regenerating the test runs in the test cycle
     * Path parameters:
     * 1. testCycleId (required)
     * Body: RequestTestCycle
     */
    AbstractRestResponse updateTestCycle(RequestTestCycle testCycle, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.TEST_CYCLES_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(testCycle), null, AbstractRestResponse.class, poolUtils, headers);
    }
}
