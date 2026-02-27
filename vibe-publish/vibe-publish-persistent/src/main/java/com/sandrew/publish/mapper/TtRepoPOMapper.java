package com.sandrew.publish.mapper;

import com.sandrew.publish.model.TtRepoPO;
import com.sandrew.publish.model.TtRepoPOExample;
import java.util.List;

public interface TtRepoPOMapper {

    int countByExample(TtRepoPOExample example);

    int deleteByExample(TtRepoPOExample example);

    int deleteByPrimaryKey(Integer repoId);

    int insert(TtRepoPO record);

    int insertSelective(TtRepoPO record);

    List<TtRepoPO> selectByExample(TtRepoPOExample example);

    TtRepoPO selectByPrimaryKey(Integer repoId);

    int updateByExampleSelective(TtRepoPO record, TtRepoPOExample example);

    int updateByPrimaryKeySelective(TtRepoPO record);

    int updateByPrimaryKey(TtRepoPO record);
}
