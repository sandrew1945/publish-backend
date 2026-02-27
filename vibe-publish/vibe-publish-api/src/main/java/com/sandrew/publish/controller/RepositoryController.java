package com.sandrew.publish.controller;

import com.sandrew.publish.bean.repository.*;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.bean.PageResult;
import com.sandrew.publish.core.exception.JsonException;
import com.sandrew.publish.core.result.JsonResult;
import com.sandrew.publish.service.RepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Repository Management.
 * Covers repositories, modules, endpoints (APIs), parameters, and endpoint
 * testing.
 */
@Tag(name = "Repository Management", description = "Manage repositories, modules, endpoints and parameters")
@RestController
@RequestMapping("/repository")
@Slf4j
public class RepositoryController extends BaseController {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RepositoryConvertor repositoryConvertor;

    // ─── Repository ──────────────────────────────────────────────────────────

    @Operation(summary = "Paginated repository query")
    @PostMapping("/repoPageQuery")
    public @ResponseBody JsonResult repoPageQuery(
            @RequestParam(required = false) String repoName,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer ownerId,
            int limit,
            int curPage) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            RepoDTO condition = new RepoDTO();
            condition.setRepoName(repoName);
            condition.setStatus(status);
            condition.setOwnerId(ownerId);

            PageResult<RepoBO> pageResult = repositoryService.repoPageQuery(condition, limit, curPage);
            PageResult<RepoPageQueryVO> voPage = pageResult.convert(
                    (List boList) -> repositoryConvertor.toRepoPageQueryVOList(boList));
            result.requestSuccess(voPage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Create a new repository")
    @PostMapping("/createRepo")
    public @ResponseBody JsonResult createRepo(@RequestBody RepoDTO dto) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.createRepo(dto, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Delete repository (logic delete)")
    @PostMapping("/deleteRepo")
    public @ResponseBody JsonResult deleteRepo(@RequestParam Integer repoId) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.deleteRepo(repoId, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Update repository info and collaborators")
    @PostMapping("/updateRepo")
    public @ResponseBody JsonResult updateRepo(@RequestBody RepoDTO dto) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.updateRepo(dto, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    // ─── Module ──────────────────────────────────────────────────────────────

    @Operation(summary = "Paginated module query under a repository")
    @PostMapping("/modulePageQuery")
    public @ResponseBody JsonResult modulePageQuery(
            @RequestParam(required = false) Integer repoId,
            @RequestParam(required = false) String moduleName,
            int limit,
            int curPage) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            ModuleDTO condition = new ModuleDTO();
            condition.setRepoId(repoId);
            condition.setModuleName(moduleName);

            PageResult<ModuleBO> pageResult = repositoryService.modulePageQuery(condition, limit, curPage);
            PageResult<ModulePageQueryVO> voPage = pageResult.convert(
                    (List boList) -> repositoryConvertor.toModulePageQueryVOList(boList));
            result.requestSuccess(voPage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Create a new module under a repository")
    @PostMapping("/createModule")
    public @ResponseBody JsonResult createModule(@RequestBody ModuleDTO dto) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.createModule(dto, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Delete module (logic delete)")
    @PostMapping("/deleteModule")
    public @ResponseBody JsonResult deleteModule(@RequestParam Integer moduleId) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.deleteModule(moduleId, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    // ─── Endpoint (API) ───────────────────────────────────────────────────────

    @Operation(summary = "Paginated endpoint query under a module")
    @PostMapping("/apiPageQuery")
    public @ResponseBody JsonResult apiPageQuery(
            @RequestParam(required = false) Integer moduleId,
            @RequestParam(required = false) String apiName,
            int limit,
            int curPage) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            ApiDTO condition = new ApiDTO();
            condition.setModuleId(moduleId);
            condition.setApiName(apiName);

            PageResult<ApiBO> pageResult = repositoryService.apiPageQuery(condition, limit, curPage);
            PageResult<ApiPageQueryVO> voPage = pageResult.convert(
                    (List boList) -> repositoryConvertor.toApiPageQueryVOList(boList));
            result.requestSuccess(voPage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Create a new endpoint under a module")
    @PostMapping("/createApi")
    public @ResponseBody JsonResult createApi(@RequestBody ApiDTO dto) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.createApi(dto, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Delete endpoint (logic delete)")
    @PostMapping("/deleteApi")
    public @ResponseBody JsonResult deleteApi(@RequestParam Integer apiId) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.deleteApi(apiId, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    // ─── Parameters ──────────────────────────────────────────────────────────

    @Operation(summary = "Save (replace) all parameters for an endpoint")
    @PostMapping("/saveParams")
    public @ResponseBody JsonResult saveParams(
            @RequestParam Integer apiId,
            @RequestBody List<ParamDTO> params) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            AclUserBean aclUser = getLoginUser();
            Boolean success = repositoryService.saveParams(apiId, params, aclUser);
            result.requestSuccess(success);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    // ─── Endpoint Testing ────────────────────────────────────────────────────

    @Operation(summary = "Execute a live HTTP test against a testable endpoint")
    @PostMapping("/testApi")
    public @ResponseBody JsonResult testApi(@RequestBody ApiTestDTO dto) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            ApiTestResultVO testResult = repositoryService.testApi(dto);
            result.requestSuccess(testResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }
}
