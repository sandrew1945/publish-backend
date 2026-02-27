package com.sandrew.publish.mapper;

import com.sandrew.publish.model.TtModulePO;
import com.sandrew.publish.model.TtModulePOExample;
import java.util.List;

public interface TtModulePOMapper {

    int countByExample(TtModulePOExample example);

    int deleteByExample(TtModulePOExample example);

    int deleteByPrimaryKey(Integer moduleId);

    int insert(TtModulePO record);

    int insertSelective(TtModulePO record);

    List<TtModulePO> selectByExample(TtModulePOExample example);

    TtModulePO selectByPrimaryKey(Integer moduleId);

    int updateByExampleSelective(TtModulePO record, TtModulePOExample example);

    int updateByPrimaryKeySelective(TtModulePO record);

    int updateByPrimaryKey(TtModulePO record);
}
