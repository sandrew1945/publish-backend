package com.sandrew.publish.mapper;

import com.sandrew.publish.model.TtParamPO;
import com.sandrew.publish.model.TtParamPOExample;
import java.util.List;

public interface TtParamPOMapper {

    int countByExample(TtParamPOExample example);

    int deleteByExample(TtParamPOExample example);

    int deleteByPrimaryKey(Integer paramId);

    int insert(TtParamPO record);

    int insertSelective(TtParamPO record);

    List<TtParamPO> selectByExample(TtParamPOExample example);

    TtParamPO selectByPrimaryKey(Integer paramId);

    int updateByExampleSelective(TtParamPO record, TtParamPOExample example);

    int updateByPrimaryKeySelective(TtParamPO record);

    int updateByPrimaryKey(TtParamPO record);
}
