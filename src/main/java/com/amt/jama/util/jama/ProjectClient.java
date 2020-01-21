package com.amt.jama.util.jama;

import com.amt.jama.connect.constants.HttpUrls;
import com.amt.jama.connect.pools.HttpPoolUtils;
import com.amt.jama.core.po.abstractitem.AbstractRestResponse;
import com.amt.jama.core.po.createdresponse.CreatedResponse;
import com.amt.jama.core.po.itemtypes.ItemTypeDataListWrapper;
import com.amt.jama.core.po.projects.ProjectDataListWrapper;
import com.amt.jama.core.po.projects.ProjectDataWrapper;
import com.amt.jama.core.po.request.RequestAttachment;
import com.amt.jama.core.po.request.RequestProject;
import com.amt.jama.core.po.tags.TagDataListWrapper;
import com.amt.jama.util.json.FastJsonUtils;

import java.util.Map;

/**
 * @author wikle.wang
 */
public class ProjectClient extends BaseClient {

    ProjectClient(String restVersion) {
        super(restVersion);
    }

    /**
     * GET: Add an Item Type to a Project
     * Path parameters:
     * 1. projectId (required)
     * 2. itemTypeId (required)
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse addItemType(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) put(HttpUrls.PROJECTS_ITEM_TYPES_ID_PUT, pathParameters, null, null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * POST: Create a new project
     * Body: RequestProject
     *
     * @return CreatedResponse
     */
    CreatedResponse createdProject(RequestProject project, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.PROJECTS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(project), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * GET: Get all item types for the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ItemTypeDataListWrapper
     */
    ItemTypeDataListWrapper getItemTypes(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ItemTypeDataListWrapper) get(HttpUrls.PROJECTS_ID_ITEM_TYPES, pathParameters, queryParameters, ItemTypeDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Query parameters:
     * 1. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return ProjectDataWrapper
     */
    ProjectDataWrapper getProject(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ProjectDataWrapper) get(HttpUrls.PROJECTS_ID, pathParameters, queryParameters, ProjectDataWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all projects
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     */
    ProjectDataListWrapper getProjects(Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (ProjectDataListWrapper) get(HttpUrls.PROJECTS, null, queryParameters, ProjectDataListWrapper.class, poolUtils, headers);
    }

    /**
     * GET: Get all tags for the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Query parameters:
     * 1. startAt (optional)
     * 2. maxResults (optional)
     * If not set, this defaults to 20. This cannot be larger than 50
     * 3. include (optional)
     * Links to include as full objects in the linked map
     *
     * @return TagDataListWrapper
     */
    TagDataListWrapper getTags(Map<String, String> pathParameters, Map<String, String> queryParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (TagDataListWrapper) get(HttpUrls.PROJECTS_ID_TAGS, pathParameters, queryParameters, TagDataListWrapper.class, poolUtils, headers);
    }

    /**
     * POST:  Create a new attachment in the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Body: RequestAttachment
     *
     * @return CreatedResponse
     */
    CreatedResponse addAttachment(RequestAttachment attachment, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (CreatedResponse) post(HttpUrls.PROJECTS_ID_ATTACHMENTS_POST, pathParameters, FastJsonUtils.toJSONNoFeatures(attachment), null, CreatedResponse.class, poolUtils, headers);
    }

    /**
     * PUT: Update the project with the specified ID
     * Path parameters:
     * 1. projectId (required)
     * Body: RequestProject
     *
     * @return AbstractRestResponse
     */
    AbstractRestResponse updateProject(RequestProject project, Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) post(HttpUrls.PROJECTS_ID_PUT, pathParameters, FastJsonUtils.toJSONNoFeatures(project), null, AbstractRestResponse.class, poolUtils, headers);
    }

    /**
     * DELETE: Remove an Item Type to a Project
     * Path parameters:
     * 1. projectId (required)
     * 2. itemTypeId (required)
     */
    AbstractRestResponse removeIssueType(Map<String, String> pathParameters, HttpPoolUtils poolUtils, Map<String, String> headers) throws Exception {
        return (AbstractRestResponse) delete(HttpUrls.PROJECTS_ITEM_TYPES_ID_DELETE, pathParameters, AbstractRestResponse.class, poolUtils, headers);
    }
}
