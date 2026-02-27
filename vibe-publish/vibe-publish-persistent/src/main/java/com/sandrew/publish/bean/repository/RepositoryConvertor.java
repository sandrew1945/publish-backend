package com.sandrew.publish.bean.repository;

import com.sandrew.publish.model.TtParamPO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * MapStruct convertor for repository-related entity conversions.
 *
 * NOTE: RepoBO is mapped from custom SQL results (not TtRepoPO directly),
 * so BO → VO conversions are the main use case here.
 */
@Mapper(componentModel = "spring")
public interface RepositoryConvertor {

    RepoPageQueryVO toRepoPageQueryVO(RepoBO repoBO);

    List<RepoPageQueryVO> toRepoPageQueryVOList(List<RepoBO> repoBOList);

    ModulePageQueryVO toModulePageQueryVO(ModuleBO moduleBO);

    List<ModulePageQueryVO> toModulePageQueryVOList(List<ModuleBO> moduleBOList);

    ApiPageQueryVO toApiPageQueryVO(ApiBO apiBO);

    List<ApiPageQueryVO> toApiPageQueryVOList(List<ApiBO> apiBOList);

    ParamVO toParamVO(ParamBO paramBO);

    List<ParamVO> toParamVOList(List<ParamBO> paramBOList);

    ParamBO toParamBO(TtParamPO ttParamPO);

    List<ParamBO> toParamBOList(List<TtParamPO> ttParamPOList);
}
