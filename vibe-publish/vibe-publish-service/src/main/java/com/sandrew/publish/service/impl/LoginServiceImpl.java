/**
 * <pre>
 * FILE : LoginService.java
 * CLASS : LoginService
 *
 * AUTHOR : SuMMeR
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2014年5月3日| SuMMeR| Created |
 * DESCRIPTION:
 * </pre>
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */
/**
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */

package com.sandrew.publish.service.impl;


import com.sandrew.publish.bean.RoleBean;
import com.sandrew.publish.bean.login.LoginBO;
import com.sandrew.publish.bean.login.LoginConvertor;
import com.sandrew.publish.bean.rolemanager.RoleManagerConvertor;
import com.sandrew.publish.bean.usermanager.UserInfoVO;
import com.sandrew.publish.bean.usermanager.UserManagerBO;
import com.sandrew.publish.bean.usermanager.UserManagerConvertor;
import com.sandrew.publish.dictionary.Constants;
import com.sandrew.publish.mapper.custom.LoginMapper;
import com.sandrew.publish.model.TmMenuPO;
import com.sandrew.publish.model.TmRolePO;
import com.sandrew.publish.model.TmRoleVO;
import com.sandrew.publish.model.TmUserPO;
import com.sandrew.publish.service.LoginService;
import com.sandrew.publish.service.RoleManagerService;
import com.sandrew.publish.service.UserManagerService;
import com.sandrew.publish.service.util.MenuNode;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.exception.ServiceException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2014年5月3日
 * @version    :
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService
{
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private UserManagerService userManagerService;
    @Resource
    private RoleManagerService roleManagerService;
    @Resource
    private LoginConvertor loginConvertor;
    @Resource
    private RoleManagerConvertor roleManagerConvertor;
    @Resource
    private UserManagerConvertor userManagerConvertor;

    @Override
    public LoginBO login(AuthenticationToken token) throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
//            MyUsernamePasswordToken token = new MyUsernamePasswordToken(user.getUserCode(), user.getPassword());
            LoginBO result = new LoginBO();
            if (!subject.isAuthenticated())
            {
                subject.login(token);
                AclUserBean loginUser = (AclUserBean) ThreadContext.get("loginUser");
                log.debug("After login, fetch user from database, usercode ======> " + loginUser.getUserCode());
                TmUserPO databaseUser = userManagerService.getUserByCode(loginUser.getUserCode());
                loginUser.setUserId(databaseUser.getUserId());
                loginUser.setUserCode(databaseUser.getUserCode());
                loginUser.setUserName(databaseUser.getUserName());
                loginUser.setToken(subject.getSession().getId().toString());
                subject.getSession().setAttribute(Constants.LOGIN_USER, loginUser);
                result = loginConvertor.toLoginBO(loginUser);
                return result;
            }
            else
            {
                AclUserBean loginUser = (AclUserBean) subject.getSession().getAttribute(Constants.LOGIN_USER);
                result = loginConvertor.toLoginBO(loginUser);
                return result;
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("登录失败", e);
        }
    }

    @Override
    public UserInfoVO userInfo(AclUserBean loginUser) throws ServiceException
    {
        try
        {
            UserInfoVO userInfo = new UserInfoVO();
            UserManagerBO user = userManagerService.findByUserId(loginUser.getUserId());
            userInfo = loginConvertor.toUserInfoVO(user);
            List<TmRoleVO> roleList = roleManagerConvertor.toRoleVOList(userManagerService.getRelationRolesByUserId(loginUser.getUserId()));
            userInfo.setRoleList(roleList);
            return userInfo;
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户信息失败", e);
        }
    }

    @Override
    public RoleBean setCurrentlyRole(Integer roleId) throws ServiceException
    {
        try
        {
            TmRolePO role = roleManagerService.findByroleId(roleId);
            if (null != role)
            {
                Subject subject = SecurityUtils.getSubject();
                AclUserBean loginUser = (AclUserBean) subject.getSession().getAttribute(Constants.LOGIN_USER);
                loginUser.setRoleId(role.getRoleId());
                loginUser.setRoleCode(role.getRoleCode());
                loginUser.setRoleType(role.getRoleType());
                loginUser.setRoleName(role.getRoleName());
                subject.getSession().setAttribute(Constants.LOGIN_USER, loginUser);
                RoleBean roleBean = new RoleBean();
                roleBean.setRoleId(role.getRoleId());
                roleBean.setRoleCode(role.getRoleCode());
                roleBean.setRoleName(role.getRoleName());
                roleBean.setRoleType(role.getRoleType());
                return roleBean;
            }
            else
            {
                throw new ServiceException("Role information was not found.");
            }
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to set user role information", e);
        }
    }

    @Override
    public boolean validateSession(String sessionId) throws ServiceException
    {
        try
        {
            DefaultSessionKey sessionKey = new DefaultSessionKey(sessionId);
            Session session = SecurityUtils.getSecurityManager().getSession(sessionKey);
            if (null != session)
            {
                return true;
            }

        }
        catch (Exception e)
        {
            log.debug("There is no session with id [{}]", sessionId);
//            throw new ServiceException("获取session失败", e);
        }
        return false;
    }

    @Override
    public List<MenuNode> getMenuByRole(Integer roleId) throws ServiceException
    {
        try
        {
            List<TmMenuPO> functionList = loginMapper.getMenuByRole(roleId);

            List<MenuNode> menuList = new ArrayList<>();
            Map<Integer, MenuNode> cache = new HashMap<>();
            for (TmMenuPO function : functionList)
            {
                MenuNode menu = new MenuNode();
                menu.setPath(function.getPath());
                menu.setName(function.getTitle());
                Map<String, String> meta = new HashMap<>();
                meta.put("title", function.getTitle());
                meta.put("icon", function.getIcon());
                menu.setMeta(meta);
                cache.put(function.getMenuId(), menu);
                if (null != function.getFatherId())
                {
                    // 如果存在父节点，那么将子节点添加到父节点，并且不添加到menuList中
                    MenuNode father = cache.get(function.getFatherId());
                    father.addChildren(menu);
                    continue;
                }
                menuList.add(menu);
            }
            return menuList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取系统菜单失败", e);
        }
    }

    @Override
    public void logout() throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("登出系统失败", e);
        }
    }
}
