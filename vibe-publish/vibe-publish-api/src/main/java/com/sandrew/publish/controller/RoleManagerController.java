/**********************************************************************
* <pre>
* FILE : RoleManagerController.java
* CLASS : RoleManagerController
*
* AUTHOR : Liutt
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2016年5月30日| Liutt| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: RoleManagerController.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp $
*/
package com.sandrew.publish.controller;

import com.sandrew.publish.model.TmRolePO;
import com.sandrew.publish.param.FunctionsParam;
import com.sandrew.publish.service.RoleManagerService;
import com.sandrew.publish.core.exception.JsonException;
import com.sandrew.publish.core.exception.ServiceException;
import com.sandrew.publish.core.result.JsonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Function :
 * 
 * @author : liutt
 *         CreateDate : 2016年5月30日
 * @version :
 */
@Slf4j
@RestController
@RequestMapping("/rolemanager")
@Tag(name = "Role Management", description = "Role CRUD, permission assignment, and validation")
public class RoleManagerController extends BaseController {
	@Resource
	private RoleManagerService roleManagerService;// 角色处理的service

	@Operation(summary = "Paginated role query", description = "Query roles with optional filters for code, name, and status")
	@PostMapping(value = "/roleManagerPageQuery")
	public JsonResult userManagerPageQuery(@RequestParam(required = false) String roleCode,
			@RequestParam(required = false) String roleName, @RequestParam(required = false) Integer roleStatus,
			int limit, int curPage) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			TmRolePO condition = new TmRolePO();
			condition.setRoleCode(roleCode);
			condition.setRoleName(roleName);
			condition.setRoleStatus(roleStatus);
			return result.requestSuccess(roleManagerService.roleManagerPageQuery(condition, limit, curPage));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Get role by ID", description = "Retrieve role details by role ID")
	@GetMapping("getRoleInfoById")
	public JsonResult getRoleInfoById(Integer roleId) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			TmRolePO role = roleManagerService.findByroleId(roleId);
			return result.requestSuccess(role);
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
			throw new JsonException("查询角色失败", e);
		}

	}

	@Operation(summary = "Create role", description = "Create a new role")
	@PostMapping(value = "/createRole")
	public JsonResult createRole(TmRolePO user) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(roleManagerService.createRole(user, getLoginUser()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Update role", description = "Update an existing role's information")
	@PostMapping(value = "/updateRole")
	public JsonResult updateRole(TmRolePO role) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(roleManagerService.updateRole(role, getLoginUser()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Delete role", description = "Delete a role if it is not assigned to any user")
	@PostMapping(value = "/deleteRole")
	public JsonResult deleteRole(Integer roleId) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			// 删除角色的时候 需要判断 该角色是否分配给其他人 如果未分配则可以删除 则不可以删除
			return result.requestSuccess(roleManagerService.deleteRole(roleId, getLoginUser()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	/**
	 * 保存角色权限， 用于quasar
	 * 
	 * @param parameter
	 * @return
	 * @throws JsonException
	 */
	@Operation(summary = "Save role permissions", description = "Save the permission (function) assignments for a role")
	@PostMapping("saveSelectedFunc")
	public JsonResult saveSelectedFunc(@RequestBody FunctionsParam parameter) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(roleManagerService.saveSelectedFunc(parameter.getRoleId(),
					parameter.getFunctionIds(), getLoginUser()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException("保存权限失败", e);
		}
	}

	/**
	 * @Author summer
	 * @Description 查询已选菜单
	 * @Date 16:43 2023/11/16
	 * @Param [roleId]
	 * @return com.sandrew.publish.core.result.JsonResult
	 **/
	@Operation(summary = "Get checked permissions", description = "Retrieve the list of permissions already assigned to a role")
	@GetMapping("getCheckedPremission")
	public JsonResult getCheckedPremission(Integer roleId) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(roleManagerService.getCheckPermission(roleId));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException("保存权限失败", e);
		}
	}

	@Operation(summary = "Validate role code", description = "Check whether a role code already exists")
	@GetMapping("roleValidate")
	public JsonResult roleValidate(String roleCode) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(roleManagerService.roleValidate(roleCode));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException("角色验证失败", e);
		}
	}
}
