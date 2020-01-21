package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractItemDataWrapper;
import com.amt.jama.core.po.abstractitem.AbstractVersionedItemDataWrapper;
import com.amt.jama.core.po.items.ItemDataListWrapper;
import com.amt.jama.core.po.version.VersionDataListWrapper;
import com.amt.jama.core.po.version.VersionDataWrapper;
import com.amt.jama.core.po.version.VersionedRelationshipDataListWrapper;

import java.util.Map;

public class AbstractClient extends BaseClient {

    AbstractClient(String restVersion) {
        super(restVersion);
    }

    /**
     * GET: Search for items, test plans, test cycles, test runs, or attachments
     * Query parameters:
     * 1. project(optional)
     * 2. itemType (optional)
     * 3. documentKey (optional)
     * 4. release (optional)
     * 5. createdDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 6. modifiedDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 7. lastActivityDate (optional)
     * Filter datetime fields after a single date or within a range of values. Provide one or two values in ISO8601 format (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 8. contains (optional)
     * Filter on the text contents of the item. Strings taken literally. Multiple 'contains' values will be bitwise ORed.
     * 9. sortBy (optional)
     * Sort orders can be added with the name of the field by which to sort, followed by .asc or .desc (e.g. 'name.asc' or 'modifiedDate.desc'). If not set, this defaults to sorting by sequence.asc and then documentKey.asc
     * 10. startAt (optional)
     * 11. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 12. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemDataListWrapper
     */
    ItemDataListWrapper getItems(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemDataListWrapper) get(HttpUrls.ABSTRACT_ITEMS, null, queryParameters, ItemDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get any item, test plan, test cycle, test run, or attachment with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return AbstractItemDataWrapper
     */
    AbstractItemDataWrapper getItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractItemDataWrapper) get(HttpUrls.ABSTRACT_ITEMS_ID, pathParameters, queryParameters, AbstractItemDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all versions for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return VersionDataListWrapper
     */
    VersionDataListWrapper getVersions(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataListWrapper) get(HttpUrls.ABSTRACT_ITEMS_ID_VERSION, pathParameters, queryParameters, VersionDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the numbered version for the item with the specified ID
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionDataWrapper getVersion(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionDataWrapper) get(HttpUrls.ABSTRACT_ITEMS_ID_VERSION_NUM, pathParameters, queryParameters, VersionDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the snapshot of the item at the specified version
     * Path parameters:
     * 1. id (required)
     * 2. versionNum (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return AbstractVersionedItemDataWrapper
     */
    AbstractVersionedItemDataWrapper getVersionItem(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractVersionedItemDataWrapper) get(HttpUrls.ABSTRACT_ITEMS_ID_VERSION_NUM_ITEM, pathParameters, queryParameters, AbstractVersionedItemDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all versioned relationships that were associated to the item at the specified time
     * Path parameters:
     * id (required)
     * Query parameters:
     * 1. timestamp (required)
     * Get relationships for the specified item at this date and time. Requires ISO8601 formatting (milliseconds or seconds) - \"yyyy-MM-dd'T'HH:mm:ss.SSSZ\" or \"yyyy-MM-dd'T'HH:mm:ssZ\"
     * 2. startAt (optional)
     * 3.  maxResults (optional)
     * not set, this defaults to 20. This cannot be larger than 50
     * 4. include (optional)
     * Links to include as full objects in the linked map
     */
    VersionedRelationshipDataListWrapper getVersionRelationships(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (VersionedRelationshipDataListWrapper) get(HttpUrls.ABSTRACT_ITEMS_ID_VERSION_RELATIONSHIPS, pathParameters, queryParameters, VersionedRelationshipDataListWrapper.class, poolUtils, headers);
    }
}
