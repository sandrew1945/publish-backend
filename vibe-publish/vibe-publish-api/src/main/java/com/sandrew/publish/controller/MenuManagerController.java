package com.sandrew.publish.controller;

import com.sandrew.publish.service.MenuManagerService;
import com.sandrew.publish.service.util.TreeNode;
import com.sandrew.publish.core.exception.JsonException;
import com.sandrew.publish.core.exception.ServiceException;
import com.sandrew.publish.core.result.JsonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function :
 * 
 * @author : liutt
 *         CreateDate : 2016年5月30日
 * @version :
 */
@Slf4j
@RestController
@RequestMapping("/menumanager")
@Tag(name = "Menu Management", description = "Menu tree CRUD operations")
public class MenuManagerController extends BaseController {
	@Resource
	private MenuManagerService menuManagerService;

	@Operation(summary = "Get menu tree", description = "Retrieve the full hierarchical menu tree")
	@GetMapping("getMenuTree")
	public JsonResult getMenuTree() throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(menuManagerService.getMenuTree());
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Create menu", description = "Create a new menu item under the specified parent")
	@PostMapping(value = "/createMenu")
	public JsonResult createMenu(TreeNode treeNode, Integer fatherId) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(menuManagerService.createMenu(treeNode, fatherId, getLoginUser()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Update menu", description = "Update an existing menu item")
	@PostMapping(value = "/updateMenu")
	public JsonResult updateMenu(TreeNode treeNode) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(menuManagerService.updateMenu(treeNode, getLoginUser()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@Operation(summary = "Delete menu", description = "Delete a menu item by its function ID")
	@PostMapping(value = "/deleteMenu")
	public JsonResult deleteMenu(Integer functionId) throws JsonException {
		JsonResult result = new JsonResult();
		try {
			return result.requestSuccess(menuManagerService.deleteMenuById(functionId, getLoginUser()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

}
