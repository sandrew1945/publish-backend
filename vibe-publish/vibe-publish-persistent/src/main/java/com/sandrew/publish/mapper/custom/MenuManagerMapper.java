package com.sandrew.publish.mapper.custom;


import com.sandrew.publish.model.TmMenuPO;

import java.util.List;


public interface MenuManagerMapper
{
	// 查询系统菜单
	public List<TmMenuPO> getMenuList();

}