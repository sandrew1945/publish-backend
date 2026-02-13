package com.sandrew.publish.service;


import com.sandrew.publish.bean.RoleBean;
import com.sandrew.publish.bean.login.LoginBO;
import com.sandrew.publish.bean.usermanager.UserInfoVO;
import com.sandrew.publish.service.util.MenuNode;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.exception.ServiceException;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.List;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface LoginService extends BaseService
{
	/**
	 *  登录
	 * @param token
	 * @return
	 * @throws ServiceException
	 */
	LoginBO login(AuthenticationToken token) throws ServiceException;

	/**
	 *  Set current login user's role information
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	RoleBean setCurrentlyRole(Integer roleId) throws ServiceException;

	/**
	 *  获取用户信息
	 * @return
	 * @throws ServiceException
	 */
	UserInfoVO userInfo(AclUserBean loginUser) throws ServiceException;

	/**
	 * @Author summer
	 * @Description	根据sessionId获取session
	 * @Date 14:35 2022/2/14
	 * @Param [sessionId]
	 * @return boolean
	 **/
	boolean validateSession(String sessionId) throws ServiceException;

	/**
	 *
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	List<MenuNode> getMenuByRole(Integer roleId) throws ServiceException;

	/**
	 *  登出
	 * @throws ServiceException
	 */
	void logout() throws ServiceException;
	
}
