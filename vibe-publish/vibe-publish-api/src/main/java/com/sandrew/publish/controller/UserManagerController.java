package com.sandrew.publish.controller;


import com.sandrew.publish.bean.rolemanager.RoleManagerConvertor;
import com.sandrew.publish.bean.usermanager.*;
import com.sandrew.publish.service.UserManagerService;
import com.sandrew.publish.core.bean.AclUserBean;
import com.sandrew.publish.core.bean.PageResult;
import com.sandrew.publish.core.exception.JsonException;
import com.sandrew.publish.core.exception.ServiceException;
import com.sandrew.publish.core.result.JsonResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usermanager")
public class UserManagerController extends BaseController
{
    @Resource
    private UserManagerService userManagerService;

    @Resource
    private UserManagerConvertor userManagerConvertor;

    @Resource
    private RoleManagerConvertor roleManagerConvertor;

    @PostMapping(value = "/userManagerPageQuery")
    public
    @ResponseBody JsonResult userManagerPageQuery(@RequestParam(required = false) String userCode, @RequestParam(required = false) String userName, @RequestParam(required = false) Integer userStatus, int limit, int curPage) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            UserManagerDTO condition = new UserManagerDTO();
            condition.setUserCode(userCode);
            condition.setUserName(userName);
            condition.setUserStatus(userStatus);
            // BO转VO
            PageResult<UserManagerBO> pageResult = userManagerService.userManagerPageQuery(condition, limit, curPage);

            PageResult<UserPageQueryVO> pr = pageResult.convert(originPageResult -> {
                return userManagerConvertor.toUserPageQueryVO(originPageResult);
            });
            result.requestSuccess(pr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @GetMapping("getUserInfoById")
    public JsonResult getUserInfoById(Integer userId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            UserManagerBO user = userManagerService.findByUserId(userId);
            result.requestSuccess(userManagerConvertor.toUserVO(user));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
        return result;
    }

    @PostMapping(value = "/createUserInfo")
    public JsonResult createUserInfo(UserManagerDTO user) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            result.requestSuccess(userManagerService.createUserInfo(user, null, getLoginUser()));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @PostMapping(value = "/createUserInfoForJsonBody")
    public JsonResult createUserInfoForJsonBody(@RequestBody UserManagerDTO user) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            result.requestSuccess(userManagerService.createUserInfo(user, null, getLoginUser()));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }


    @PostMapping(value = "/updateUserInfo")
    public JsonResult updateUserInfo(UserManagerDTO user) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            int count = userManagerService.updateUserInfo(user, null, getLoginUser());
            result.requestSuccess(count > 0);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @PostMapping(value = "/deleteUserInfo")
    public JsonResult deleteUserInfo(Integer userId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            result.requestSuccess(userManagerService.deleteUserInfo(userId, getLoginUser()));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    @DeleteMapping(value = "/deleteUserInfo/{userId}")
    public JsonResult deleteUserInfoByPathVariable(@PathVariable Integer userId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.deleteUserInfo(userId, getLoginUser()));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/queryRelationRoles")
    public JsonResult queryRelationRoles(Integer userId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(roleManagerConvertor.toRoleVOList(userManagerService.getRelationRolesByUserId(userId)));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/deleteRoleRelation")
    public JsonResult deleteRoleRelation(Integer userId, Integer roleId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.deleteRoleRelation(userId, roleId));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/queryUnRelationRoles")
    public JsonResult queryUnRelationRoles(Integer userId, String roleName) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            AclUserBean aclUser = new AclUserBean();
            aclUser.setUserId(userId);
            aclUser.setRoleName(roleName);
            return result.requestSuccess(roleManagerConvertor.toRoleVOList(userManagerService.getUnRelationRoles(aclUser)));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/createRelation")
    public void createRelation(Integer userId, String rolesStr) throws JsonException
    {
        try
        {
            userManagerService.createRelation(userId, rolesStr, getLoginUser());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/getUserList")
    public JsonResult getUserList() throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.getAvailableUserList());
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/updatePassword")
    public JsonResult updatePassword(Integer userId, String originPwd, String newPwd) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.updatePassword(userId, originPwd, newPwd, getLoginUser()));
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }

    }

    @GetMapping(value = "/userValidate")
    public @ResponseBody JsonResult userValidate(String userCode) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.userValidate(userCode));
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/getUserListByRoleType")
    public JsonResult<List<UserInfoVO>> getUserListByRoleType(@RequestParam(required = false) Integer roleType) throws JsonException
    {
        JsonResult<List<UserInfoVO>> result = new JsonResult<>();
        try
        {
            List<UserManagerBO> userList = userManagerService.getUserListByRoleType(roleType);
            return result.requestSuccess(userManagerConvertor.toUserInfoVO(userList));
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }
}
