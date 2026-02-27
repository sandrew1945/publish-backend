package com.sandrew.publish.mapper;

import com.sandrew.publish.model.TtApiPO;
import com.sandrew.publish.model.TtApiPOExample;
import java.util.List;

public interface TtApiPOMapper {

    int countByExample(TtApiPOExample example);

    int deleteByExample(TtApiPOExample example);

    int deleteByPrimaryKey(Integer apiId);

    int insert(TtApiPO record);

    int insertSelective(TtApiPO record);

    List<TtApiPO> selectByExample(TtApiPOExample example);

    TtApiPO selectByPrimaryKey(Integer apiId);

    int updateByExampleSelective(TtApiPO record, TtApiPOExample example);

    int updateByPrimaryKeySelective(TtApiPO record);

    int updateByPrimaryKey(TtApiPO record);
}
