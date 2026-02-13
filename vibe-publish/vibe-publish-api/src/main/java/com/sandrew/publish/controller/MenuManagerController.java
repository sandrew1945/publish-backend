package com.sandrew.publish.controller;


import com.sandrew.publish.service.MenuManagerService;
import com.sandrew.publish.service.util.TreeNode;
import com.sandrew.publish.core.exception.JsonException;
import com.sandrew.publish.core.exception.ServiceException;
import com.sandrew.publish.core.result.JsonResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Function    : 
 * @author     : liutt
 * CreateDate  : 2016年5月30日
 * @version    :
 */
@Slf4j
@RestController
@RequestMapping("/menumanager")
public class MenuManagerController extends BaseController
{
	@Resource
	private MenuManagerService menuManagerService;



	@GetMapping("getMenuTree")
	public JsonResult getMenuTree() throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.getMenuTree());
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/createMenu")
	public JsonResult createMenu(TreeNode treeNode, Integer fatherId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.createMenu(treeNode, fatherId, getLoginUser()));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/updateMenu")
	public JsonResult updateMenu(TreeNode treeNode) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.updateMenu(treeNode, getLoginUser()));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/deleteMenu")
	public JsonResult deleteMenu(Integer functionId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			return result.requestSuccess(menuManagerService.deleteMenuById(functionId, getLoginUser()));
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}


}
