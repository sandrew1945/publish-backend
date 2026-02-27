package com.sandrew.publish.mapper.custom;

import com.sandrew.publish.bean.repository.RepoBO;
import com.sandrew.publish.bean.repository.ApiBO;
import com.sandrew.publish.bean.repository.ModuleBO;
import com.sandrew.publish.core.mybatis.Pager;

import java.util.List;

/**
 * Custom mapper for paginated queries on repository-related entities.
 * Standard CRUD uses the auto-generated TtRepoPOMapper, TtModulePOMapper,
 * TtApiPOMapper.
 */
public interface RepositoryMapper {

    /**
     * Paginated query for repositories, joined with collaborator user names.
     * Condition class: RepoQueryDTO (repoName, status)
     */
    List<RepoBO> repoPageQuery(Pager pager);

    /**
     * Paginated query for modules under a specific repository.
     * Condition class: ModuleQueryDTO (repoId, moduleName)
     */
    List<ModuleBO> modulePageQuery(Pager pager);

    /**
     * Paginated query for endpoints (APIs) under a specific module.
     * Condition class: ApiQueryDTO (moduleId, apiName)
     */
    List<ApiBO> apiPageQuery(Pager pager);
}
