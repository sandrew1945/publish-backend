package com.sandrew.publish.service;

import com.sandrew.publish.bean.repository.*;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.bean.PageResult;
import com.sandrew.publish.core.exception.ServiceException;

import java.util.List;

/**
 * Service interface for Repository Management feature.
 * Covers repositories, modules, endpoints (APIs), parameters, and endpoint
 * testing.
 */
public interface RepositoryService extends BaseService {

    /**
     * Paginated query for repositories.
     * 
     * @param condition filter condition (repoName, status)
     * @param limit     page size
     * @param curPage   current page number
     * @return paginated list of RepoBO
     */
    PageResult<RepoBO> repoPageQuery(RepoDTO condition, int limit, int curPage) throws ServiceException;

    /**
     * Create a new repository with collaborators.
     * 
     * @param dto     repository info and collaborator user IDs
     * @param aclUser currently logged-in user
     * @return true if created successfully
     */
    Boolean createRepo(RepoDTO dto, AclUserBean aclUser) throws ServiceException;

    /**
     * Logic-delete a repository and its collaborator relations.
     * 
     * @param repoId  repository ID
     * @param aclUser currently logged-in user
     * @return true if deleted successfully
     */
    Boolean deleteRepo(Integer repoId, AclUserBean aclUser) throws ServiceException;

    /**
     * Update repository base info and refresh collaborators.
     * 
     * @param dto     updated repository info + collaborator IDs
     * @param aclUser currently logged-in user
     * @return true if updated successfully
     */
    Boolean updateRepo(RepoDTO dto, AclUserBean aclUser) throws ServiceException;

    /**
     * Paginated query for modules under a repository.
     * 
     * @param condition filter condition (repoId, moduleName)
     * @param limit     page size
     * @param curPage   current page number
     * @return paginated list of ModuleBO
     */
    PageResult<ModuleBO> modulePageQuery(ModuleDTO condition, int limit, int curPage) throws ServiceException;

    /**
     * Create a new module under a repository.
     * 
     * @param dto     module info
     * @param aclUser currently logged-in user
     * @return true if created successfully
     */
    Boolean createModule(ModuleDTO dto, AclUserBean aclUser) throws ServiceException;

    /**
     * Logic-delete a module.
     * 
     * @param moduleId module ID
     * @param aclUser  currently logged-in user
     * @return true if deleted successfully
     */
    Boolean deleteModule(Integer moduleId, AclUserBean aclUser) throws ServiceException;

    /**
     * Paginated query for endpoints (APIs) under a module.
     * 
     * @param condition filter condition (moduleId, apiName)
     * @param limit     page size
     * @param curPage   current page number
     * @return paginated list of ApiBO
     */
    PageResult<ApiBO> apiPageQuery(ApiDTO condition, int limit, int curPage) throws ServiceException;

    /**
     * Create a new endpoint under a module.
     * 
     * @param dto     endpoint info
     * @param aclUser currently logged-in user
     * @return true if created successfully
     */
    Boolean createApi(ApiDTO dto, AclUserBean aclUser) throws ServiceException;

    /**
     * Logic-delete an endpoint.
     * 
     * @param apiId   endpoint ID
     * @param aclUser currently logged-in user
     * @return true if deleted successfully
     */
    Boolean deleteApi(Integer apiId, AclUserBean aclUser) throws ServiceException;

    /**
     * Save (replace) all parameters for an endpoint.
     * Existing params are logic-deleted before inserting the new list.
     * 
     * @param apiId   endpoint ID
     * @param params  full list of parameters to save
     * @param aclUser currently logged-in user
     * @return true if saved successfully
     */
    Boolean saveParams(Integer apiId, List<ParamDTO> params, AclUserBean aclUser) throws ServiceException;

    /**
     * Execute an HTTP test against a testable endpoint.
     * Builds URL from module url_base + api api_path.
     * 
     * @param dto test input (apiId, testType, params/body)
     * @return test result (statusCode, responseBody, elapsedMs)
     */
    ApiTestResultVO testApi(ApiTestDTO dto) throws ServiceException;
}
