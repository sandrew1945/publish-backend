package com.sandrew.publish.mapper.custom;


import com.sandrew.publish.model.TmMenuPO;
import com.sandrew.publish.model.TmUserPO;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.mybatis.Pager;

import java.util.List;

public interface LoginMapper
{
	List<TmUserPO> selectByUserCode(TmUserPO userPO);

	List<TmUserPO> pageQueryUser(Pager pager);

	List<AclUserBean> selectRoleByUserCode(String userCode);

	List<TmMenuPO> getMenuByRole(Integer roleId);
}