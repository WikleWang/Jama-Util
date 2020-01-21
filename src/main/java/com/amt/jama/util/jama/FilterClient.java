package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.filters.FilterDataListWrapper;
import com.amt.jama.core.po.filters.FilterDataWrapper;
import com.amt.jama.core.po.items.ItemDataListWrapper;

import java.util.Map;

public class FilterClient extends BaseClient {

    FilterClient(String restVersion) {
        super(restVersion);
    }


    /**
     * GET:  Get all filters viewable by the current user
     * Query parameters:
     * 1. project (optional)
     * 2. author (optional)
     * 3. filterScope (optional)
     * Filter scope. More than one scope can be selected
     * 4. startAt (optional)
     * 5. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 6. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return FilterDataListWrapper
     */
    FilterDataListWrapper getFilters(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (FilterDataListWrapper) get(HttpUrls.FILTERS, null, queryParameters, FilterDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the filter with the specified ID
     * Path parameters:
     * 1. filterId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return FilterDataWrapper
     */
    FilterDataWrapper getFilter(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (FilterDataWrapper) get(HttpUrls.FILTERS_ID, pathParameters, queryParameters, FilterDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all result items for the filter with the specified ID
     * Path parameters:
     * 1. filterId (required)
     * Query parameters:
     * 1. project (optional)
     * Use only for filters that run on any project, where \"projectScope\" is \"CURRENT\"
     * 2. lastActivityDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 3. startAt (optional)
     * 4. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 5. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataListWrapper
     */
    ItemDataListWrapper getFilterResult(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.FILTERS_ID_RESULTS, pathParameters, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }
}
