package com.sandrew.publish.mapper;

import com.sandrew.publish.model.TrRepoUserPO;
import com.sandrew.publish.model.TrRepoUserPOExample;
import java.util.List;

public interface TrRepoUserPOMapper {

    int countByExample(TrRepoUserPOExample example);

    int deleteByExample(TrRepoUserPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TrRepoUserPO record);

    int insertSelective(TrRepoUserPO record);

    List<TrRepoUserPO> selectByExample(TrRepoUserPOExample example);

    TrRepoUserPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(TrRepoUserPO record, TrRepoUserPOExample example);

    int updateByPrimaryKeySelective(TrRepoUserPO record);

    int updateByPrimaryKey(TrRepoUserPO record);
}
