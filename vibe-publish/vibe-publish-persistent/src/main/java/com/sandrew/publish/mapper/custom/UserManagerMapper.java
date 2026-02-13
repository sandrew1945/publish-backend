package com.sandrew.publish.mapper.custom;


import com.sandrew.publish.model.TmRolePO;
import com.sandrew.publish.model.TmUserPO;
import com.sandrew.publish.model.TmUserVO;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.mybatis.Pager;

import java.util.List;
import java.util.Map;


public interface UserManagerMapper
{
	List<TmUserPO> userManagerPageQuery(Pager pager);
	
	List<TmRolePO> queryRelationRole(Integer userId);
	
	List<TmRolePO> getRoleExistOwn(AclUserBean userBean);

	List<TmUserVO> getUserListByRoleType(Integer roleType);

	int updateClearAvatar(Map<String, Object> paramMap);
}