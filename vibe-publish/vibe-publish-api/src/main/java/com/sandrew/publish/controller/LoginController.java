package com.sandrew.publish.controller;

import com.sandrew.publish.bean.RoleBean;
import com.sandrew.publish.bean.login.LoginConvertor;
import com.sandrew.publish.bean.rolemanager.RoleManagerConvertor;
import com.sandrew.publish.bean.usermanager.UserInfoVO;
import com.sandrew.publish.bean.usermanager.UserManagerBO;
import com.sandrew.publish.model.TmRoleVO;
import com.sandrew.publish.service.LoginService;
import com.sandrew.publish.service.RoleManagerService;
import com.sandrew.publish.service.UserManagerService;
import com.sandrew.publish.core.exception.JsonException;
import com.sandrew.publish.core.exception.ServiceException;
import com.sandrew.publish.core.result.JsonResult;
import com.sandrew.publish.core.shiro.MyUsernamePasswordToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Login", description = "Authentication, session management, and user context")
public class LoginController extends BaseController {
    @Resource
    private LoginService loginService;
    @Resource
    private LoginConvertor loginConvertor;
    @Resource
    private UserManagerService userManagerService;
    @Resource
    private RoleManagerConvertor roleManagerConvertor;
    @Resource
    private RoleManagerService roleManagerService;

    @Operation(summary = "User login", description = "Authenticate with username and password, returns session token")
    @PostMapping(value = "/login")
    public JsonResult login(String userCode, String password) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            MyUsernamePasswordToken token = new MyUsernamePasswordToken();
            token.setUsername(userCode);
            token.setPassword(password.toCharArray());
            result = result.requestSuccess(loginService.login(token));
        } catch (Exception e) {
            String errorMsg = null;
            if (e instanceof ServiceException) {
                errorMsg = "用户名或密码错误";
            } else {
                errorMsg = "用户登录失败";
            }
            result.requestFailure(errorMsg);
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @Operation(summary = "Get current user info", description = "Retrieve the currently logged-in user's profile and role list")
    @GetMapping(value = "/userInfo")
    public JsonResult userInfo() throws JsonException {
        JsonResult result = new JsonResult();
        try {
            UserInfoVO userInfo = new UserInfoVO();
            UserManagerBO user = userManagerService.findByUserId(getLoginUser().getUserId());
            userInfo = loginConvertor.toUserInfoVO(user);
            List<TmRoleVO> roleList = roleManagerConvertor
                    .toRoleVOList(userManagerService.getRelationRolesByUserId(getLoginUser().getUserId()));
            userInfo.setRoleList(roleList);
            result = result.requestSuccess(userInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
        return result;
    }

    @Operation(summary = "Set active role", description = "Switch the user's currently active role in the session")
    @PostMapping(value = "/setCurrentlyRole")
    public JsonResult<RoleBean> setCurrentlyRole(Integer roleId) {
        JsonResult<RoleBean> result = new JsonResult<>();
        try {
            return result.requestSuccess(loginService.setCurrentlyRole(roleId));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException("Failed to set user role information", e);
        }
    }

    @Operation(summary = "Validate session token", description = "Check whether a Shiro session token is still valid")
    @GetMapping(value = "/validateToken")
    public JsonResult validateToken(String token) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            log.debug("token -------->" + token);
            result = result.requestSuccess(loginService.validateSession(token));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException("验证登录状态失败", e);
        }
        return result;
    }

    @Operation(summary = "Get menus by role", description = "Retrieve the menu tree for the specified role")
    @GetMapping(value = "/getMenuByRole")
    public JsonResult getMenuByRole(Integer roleId) throws JsonException {
        JsonResult result = new JsonResult();
        try {
            result = result.requestSuccess(loginService.getMenuByRole(roleId));
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            throw new JsonException("获取系统菜单失败", e);
        }
        return result;
    }

    @Operation(summary = "Logout", description = "Invalidate the current Shiro session and log the user out")
    @PostMapping(value = "/logout")
    public JsonResult logout() throws JsonException {
        JsonResult result = new JsonResult();
        try {
            loginService.logout();
            result = result.requestSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonException("登出系统失败", e);
        }
        return result;
    }

}
