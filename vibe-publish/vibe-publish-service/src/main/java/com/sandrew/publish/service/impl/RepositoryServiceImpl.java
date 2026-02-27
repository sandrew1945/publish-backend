package com.sandrew.publish.service.impl;

import com.sandrew.publish.bean.repository.*;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.bean.PageResult;
import com.sandrew.publish.core.exception.ServiceException;
import com.sandrew.publish.core.mybatis.PageQueryBuilder;
import com.sandrew.publish.dictionary.Fixcode;
import com.sandrew.publish.mapper.TrRepoUserPOMapper;
import com.sandrew.publish.mapper.TtApiPOMapper;
import com.sandrew.publish.mapper.TtModulePOMapper;
import com.sandrew.publish.mapper.TtParamPOMapper;
import com.sandrew.publish.mapper.TtRepoPOMapper;
import com.sandrew.publish.mapper.custom.RepositoryMapper;
import com.sandrew.publish.model.*;
import com.sandrew.publish.service.RepositoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of RepositoryService.
 * Covers repositories (tt_repo), modules (tt_module), endpoints (tt_api),
 * parameters (tt_param), and live HTTP endpoint testing.
 */
@Service
@Slf4j
public class RepositoryServiceImpl implements RepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private TtRepoPOMapper ttRepoPOMapper;

    @Resource
    private TrRepoUserPOMapper trRepoUserPOMapper;

    @Resource
    private TtModulePOMapper ttModulePOMapper;

    @Resource
    private TtApiPOMapper ttApiPOMapper;

    @Resource
    private TtParamPOMapper ttParamPOMapper;

    // ─── Repository ──────────────────────────────────────────────────────────

    @Override
    public PageResult<RepoBO> repoPageQuery(RepoDTO condition, int limit, int curPage) throws ServiceException {
        try {
            return PageQueryBuilder.pageQuery(repositoryMapper, "repoPageQuery", condition, curPage, limit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("Repository page query failed", e);
        }
    }

    @Override
    public Boolean createRepo(RepoDTO dto, AclUserBean aclUser) throws ServiceException {
        try {
            TtRepoPO repo = new TtRepoPO();
            repo.setRepoName(dto.getRepoName());
            repo.setRepoDesc(dto.getRepoDesc());
            repo.setStatus(dto.getStatus());
            repo.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
            repo.setCreateBy(aclUser.getUserId());
            repo.setCreateDate(new Date());

            if (ttRepoPOMapper.insertSelective(repo) <= 0) {
                return false;
            }

            // NOTE: insert collaborator relations only when a list is provided
            if (dto.getCollaboratorIds() != null && !dto.getCollaboratorIds().isEmpty()) {
                batchInsertCollaborators(repo.getRepoId(), dto.getCollaboratorIds(), aclUser);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("Create repository failed", e);
        }
    }

    @Override
    public Boolean deleteRepo(Integer repoId, AclUserBean aclUser) throws ServiceException {
        try {
            // Logic-delete the repository record
            TtRepoPO update = new TtRepoPO();
            update.setRepoId(repoId);
            update.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            update.setUpdateBy(aclUser.getUserId());
            update.setUpdateDate(new Date());
            int count = ttRepoPOMapper.updateByPrimaryKeySelective(update);
            if (count <= 0) {
                throw new ServiceException("Repository not found, repoId: " + repoId);
            }

            // Logic-delete collaborator relations
            logicDeleteCollaborators(repoId, aclUser);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean updateRepo(RepoDTO dto, AclUserBean aclUser) throws ServiceException {
        try {
            TtRepoPO update = new TtRepoPO();
            update.setRepoId(dto.getRepoId());
            update.setRepoName(dto.getRepoName());
            update.setRepoDesc(dto.getRepoDesc());
            update.setStatus(dto.getStatus());
            update.setUpdateBy(aclUser.getUserId());
            update.setUpdateDate(new Date());
            int count = ttRepoPOMapper.updateByPrimaryKeySelective(update);
            if (count <= 0) {
                throw new ServiceException("Repository not found, repoId: " + dto.getRepoId());
            }

            // NOTE: refresh collaborators — delete existing then re-insert new ones
            logicDeleteCollaborators(dto.getRepoId(), aclUser);
            if (dto.getCollaboratorIds() != null && !dto.getCollaboratorIds().isEmpty()) {
                batchInsertCollaborators(dto.getRepoId(), dto.getCollaboratorIds(), aclUser);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("Update repository failed", e);
        }
    }

    // ─── Module ──────────────────────────────────────────────────────────────

    @Override
    public PageResult<ModuleBO> modulePageQuery(ModuleDTO condition, int limit, int curPage) throws ServiceException {
        try {
            return PageQueryBuilder.pageQuery(repositoryMapper, "modulePageQuery", condition, curPage, limit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("Module page query failed", e);
        }
    }

    @Override
    public Boolean createModule(ModuleDTO dto, AclUserBean aclUser) throws ServiceException {
        try {
            TtModulePO module = new TtModulePO();
            module.setRepoId(dto.getRepoId());
            module.setModuleName(dto.getModuleName());
            module.setModuleDesc(dto.getModuleDesc());
            module.setUrlBase(dto.getUrlBase());
            module.setStatus(dto.getStatus());
            module.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
            module.setCreateBy(aclUser.getUserId());
            module.setCreateDate(new Date());
            return ttModulePOMapper.insertSelective(module) > 0;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("Create module failed", e);
        }
    }

    @Override
    public Boolean deleteModule(Integer moduleId, AclUserBean aclUser) throws ServiceException {
        try {
            TtModulePO update = new TtModulePO();
            update.setModuleId(moduleId);
            update.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            update.setUpdateBy(aclUser.getUserId());
            update.setUpdateDate(new Date());
            int count = ttModulePOMapper.updateByPrimaryKeySelective(update);
            if (count <= 0) {
                throw new ServiceException("Module not found, moduleId: " + moduleId);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // ─── Endpoint (API) ───────────────────────────────────────────────────────

    @Override
    public PageResult<ApiBO> apiPageQuery(ApiDTO condition, int limit, int curPage) throws ServiceException {
        try {
            return PageQueryBuilder.pageQuery(repositoryMapper, "apiPageQuery", condition, curPage, limit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("API page query failed", e);
        }
    }

    @Override
    public Boolean createApi(ApiDTO dto, AclUserBean aclUser) throws ServiceException {
        try {
            TtApiPO api = new TtApiPO();
            api.setRepoId(dto.getRepoId());
            api.setModuleId(dto.getModuleId());
            api.setApiName(dto.getApiName());
            api.setApiPath(dto.getApiPath());
            api.setInvokeType(dto.getInvokeType());
            api.setStatusCode(dto.getStatusCode());
            api.setStatus(dto.getStatus());
            api.setTestable(dto.getTestable());
            api.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
            api.setCreateBy(aclUser.getUserId());
            api.setCreateDate(new Date());
            return ttApiPOMapper.insertSelective(api) > 0;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("Create API failed", e);
        }
    }

    @Override
    public Boolean deleteApi(Integer apiId, AclUserBean aclUser) throws ServiceException {
        try {
            TtApiPO update = new TtApiPO();
            update.setApiId(apiId);
            update.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            update.setUpdateBy(aclUser.getUserId());
            update.setUpdateDate(new Date());
            int count = ttApiPOMapper.updateByPrimaryKeySelective(update);
            if (count <= 0) {
                throw new ServiceException("API not found, apiId: " + apiId);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    // ─── Parameters ──────────────────────────────────────────────────────────

    @Override
    public Boolean saveParams(Integer apiId, List<ParamDTO> params, AclUserBean aclUser) throws ServiceException {
        try {
            // NOTE: Replace strategy — logic-delete all existing params then insert fresh
            // list
            TtParamPOExample existingExample = new TtParamPOExample();
            existingExample.createCriteria()
                    .andApiIdEqualTo(apiId)
                    .andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
            List<TtParamPO> existingParams = ttParamPOMapper.selectByExample(existingExample);
            for (TtParamPO existing : existingParams) {
                TtParamPO deleteUpdate = new TtParamPO();
                deleteUpdate.setParamId(existing.getParamId());
                deleteUpdate.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
                deleteUpdate.setUpdateBy(aclUser.getUserId());
                deleteUpdate.setUpdateDate(new Date());
                ttParamPOMapper.updateByPrimaryKeySelective(deleteUpdate);
            }

            // Insert the new parameter list
            if (params != null) {
                for (ParamDTO dto : params) {
                    TtParamPO param = new TtParamPO();
                    param.setApiId(apiId);
                    param.setParamName(dto.getParamName());
                    param.setParamRequire(dto.getParamRequire());
                    param.setDataType(dto.getDataType());
                    param.setInitVal(dto.getInitVal());
                    param.setDescription(dto.getDescription());
                    param.setFatherId(dto.getFatherId());
                    param.setParamType(dto.getParamType());
                    param.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
                    param.setCreateBy(aclUser.getUserId());
                    param.setCreateDate(new Date());
                    ttParamPOMapper.insertSelective(param);
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("Save parameters failed", e);
        }
    }

    // ─── Endpoint Testing ────────────────────────────────────────────────────

    @Override
    public ApiTestResultVO testApi(ApiTestDTO dto) throws ServiceException {
        try {
            TtApiPO api = ttApiPOMapper.selectByPrimaryKey(dto.getApiId());
            if (api == null) {
                throw new ServiceException("API not found, apiId: " + dto.getApiId());
            }
            if (!Fixcode.IF_TYPE_YES.getCode().equals(api.getTestable())) {
                throw new ServiceException("This API is not set as testable");
            }

            // Resolve the module to get url_base
            TtModulePO module = ttModulePOMapper.selectByPrimaryKey(api.getModuleId());
            if (module == null) {
                throw new ServiceException("Module not found, moduleId: " + api.getModuleId());
            }

            // NOTE: Build full URL = module url_base + api api_path
            String fullUrl = buildUrl(module.getUrlBase(), api.getApiPath(), dto);

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest request = buildHttpRequest(api, fullUrl, dto);

            long start = System.currentTimeMillis();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            long elapsedMs = System.currentTimeMillis() - start;

            return ApiTestResultVO.builder()
                    .statusCode(response.statusCode())
                    .responseBody(response.body())
                    .elapsedMs(elapsedMs)
                    .build();
        } catch (ServiceException e) {
            throw e;
        } catch (IOException | InterruptedException e) {
            log.error("API test execution failed: {}", e.getMessage(), e);
            throw new ServiceException("API test execution failed: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("API test failed", e);
        }
    }

    // ─── Private Helpers ─────────────────────────────────────────────────────

    /**
     * Build the full URL for endpoint testing.
     * For Params mode, query parameters are appended to the URL.
     */
    private String buildUrl(String urlBase, String apiPath, ApiTestDTO dto) {
        String base = (urlBase != null ? urlBase : "") + (apiPath != null ? apiPath : "");
        if ("Params".equalsIgnoreCase(dto.getTestType())
                && dto.getParams() != null
                && !dto.getParams().isEmpty()) {
            String queryString = dto.getParams().entrySet().stream()
                    .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8)
                            + "=" + URLEncoder.encode(e.getValue() != null ? e.getValue() : "", StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));
            return base + (base.contains("?") ? "&" : "?") + queryString;
        }
        return base;
    }

    /**
     * Build the HttpRequest based on invoke type and test mode.
     * Supports Body mode (JSON) and Params mode (already encoded into URL).
     */
    private HttpRequest buildHttpRequest(TtApiPO api, String fullUrl, ApiTestDTO dto) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .timeout(Duration.ofSeconds(30));

        boolean isBody = "Body".equalsIgnoreCase(dto.getTestType());
        String bodyContent = (isBody && dto.getBody() != null) ? dto.getBody() : "";

        if (Fixcode.INVOKE_TYPE_POST.getCode().equals(api.getInvokeType())) {
            builder.header("Content-Type", "application/json");
            builder.POST(HttpRequest.BodyPublishers.ofString(bodyContent));
        } else if (Fixcode.INVOKE_TYPE_PUT.getCode().equals(api.getInvokeType())) {
            builder.header("Content-Type", "application/json");
            builder.PUT(HttpRequest.BodyPublishers.ofString(bodyContent));
        } else if (Fixcode.INVOKE_TYPE_DELETE.getCode().equals(api.getInvokeType())) {
            builder.DELETE();
        } else {
            // Default to GET
            builder.GET();
        }
        return builder.build();
    }

    /**
     * Batch insert collaborator relations for a repository.
     */
    private void batchInsertCollaborators(Integer repoId, List<Integer> collaboratorIds, AclUserBean aclUser) {
        for (Integer userId : collaboratorIds) {
            TrRepoUserPO relation = new TrRepoUserPO();
            relation.setRepoId(repoId);
            relation.setUserId(userId);
            relation.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
            relation.setCreateBy(aclUser.getUserId());
            relation.setCreateDate(new Date());
            trRepoUserPOMapper.insertSelective(relation);
        }
    }

    /**
     * Logic-delete all collaborator relations for a repository.
     */
    private void logicDeleteCollaborators(Integer repoId, AclUserBean aclUser) {
        TrRepoUserPOExample example = new TrRepoUserPOExample();
        example.createCriteria()
                .andRepoIdEqualTo(repoId)
                .andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
        List<TrRepoUserPO> relations = trRepoUserPOMapper.selectByExample(example);
        for (TrRepoUserPO relation : relations) {
            TrRepoUserPO update = new TrRepoUserPO();
            update.setId(relation.getId());
            update.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            trRepoUserPOMapper.updateByPrimaryKeySelective(update);
        }
    }
}
