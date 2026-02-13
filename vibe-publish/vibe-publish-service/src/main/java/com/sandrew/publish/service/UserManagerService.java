package com.sandrew.publish.service;

import com.sandrew.publish.bean.RoleBean;
import com.sandrew.publish.bean.usermanager.UserManagerBO;
import com.sandrew.publish.bean.usermanager.UserManagerDTO;
import com.sandrew.publish.model.TmUserPO;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.bean.PageResult;
import com.sandrew.publish.core.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface UserManagerService extends BaseService
{
	/**
	 * @Author summer
	 * @Description
	 * @Date 16:11 2023/2/24
	 * @Param [condition, limit, curPage]
	 * @return com.sandrew.publish.core.bean.PageResult<com.sandrew.publish.core.bean.AclUserBean>
	 **/
	PageResult<UserManagerBO> userManagerPageQuery(UserManagerDTO condition, int limit, int curPage) throws ServiceException;

	/**
	 * 
	 * Function    : 创建用户
	 * LastUpdate  : 2016年9月22日
	 * @param user
	 * @param avatar
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	Boolean createUserInfo(UserManagerDTO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 
	 * Function    : 删除用户信息
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	Boolean deleteUserInfo(Integer userId, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	UserManagerBO findByUserId(Integer userId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 根据用户名获取用户信息
	 * LastUpdate  : 2016年10月25日
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	TmUserPO getUserByCode(String userCode) throws ServiceException;
	
	/**
	 * 修改编辑用户信息
	 * @param user
	 * @param avatar
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	int updateUserInfo(UserManagerDTO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException;
	
	
	/**
	 * 
	 * Function    : 获取当前用户已经关联的角色
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	List<RoleBean> getRelationRolesByUserId(Integer userId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 删除用户与角色的关联
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteRoleRelation(Integer userId, Integer roleId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 获取该用户下全部未关联角色
	 * LastUpdate  : 2016年9月22日
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	List<RoleBean> getUnRelationRoles(AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 
	 * Function    : 批量添加用户与角色关系
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @param rolesStr
	 * @return
	 * @throws ServiceException
	 */
	void createRelation(Integer userId, String rolesStr, AclUserBean aclUser) throws ServiceException;

	/**
	 *  获取全部有效用户
	 * @return
	 * @throws ServiceException
     */
	List<TmUserPO> getAvailableUserList() throws ServiceException;

	/**
	 *  更新密码
	 * @param userId
	 * @param originPwd
	 * @param newPwd
	 * @return
	 * @throws ServiceException
     */
	boolean updatePassword(Integer userId, String originPwd, String newPwd, AclUserBean loginUser) throws ServiceException;

	/**
	 *  用户代码验证
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	boolean userValidate(String userCode) throws ServiceException;

	/**
	 *  Get all users who belonged to a particular role type.
	 * @param roleType
	 * @return
	 * @throws ServiceException
	 */
	List<UserManagerBO> getUserListByRoleType(Integer roleType) throws ServiceException;
}
