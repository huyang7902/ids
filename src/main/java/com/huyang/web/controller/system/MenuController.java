package com.huyang.web.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huyang.common.utils.DwzJsonUtil;
import com.huyang.common.utils.RequestUtil;
import com.huyang.dao.po.BackMenu;
import com.huyang.service.system.SystemService;
import com.huyang.web.Constants;

/**
 * 菜单控制器
 * 
 * @Project: ids-web
 * @Package com.huyang.web.controller.system
 * @author HuYang
 * @date 2017年4月23日 下午2:48:36
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Controller
@RequestMapping("/system/menu.html")
public class MenuController {

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "act=list")
	public String listSystemMenus(@RequestParam(defaultValue = "") String college,
			@RequestParam(defaultValue = "") String major, Model model) {
		List<BackMenu> menus = systemService.selectAllBackMenusWithLevelRelation();
		model.addAttribute("menus", menus);
		return "system/menu/menu_list";
	}

	@RequestMapping(params = "act=goedit")
	public String goEditor(HttpServletRequest request, HttpServletResponse response) {
		Integer id = RequestUtil.getInteger(request, "id");
		if (null != id) {
			BackMenu menuById = systemService.getMenuById(id);
			BackMenu parent = systemService.getMenuById(menuById.getParentId());
			request.setAttribute("menuTO", menuById);
			request.setAttribute("parent", parent);
		}
		return "system/menu/menu_editor";
	}

	@RequestMapping(params = "act=select")
	public String goSelect(HttpServletRequest request, HttpServletResponse response) {
		List<BackMenu> menus = systemService.getAllBackMenusWithLevelRelation();
		request.setAttribute("menus", menus);
		return "system/menu/menu_select";
	}

	@RequestMapping(params = "act=save")
	public ModelAndView save(HttpServletRequest request) {
		Integer id = RequestUtil.getInteger(request, "id");
		String name = RequestUtil.getString(request, "name");
		Integer parentId = RequestUtil.getInteger(request, "parentId");
		Integer sortOrderNo = RequestUtil.getInteger(request, "sortOrderNo");
		String url = RequestUtil.getString(request, "url");
		String remark = RequestUtil.getString(request, "remark");
		Integer accessRoleLevel = RequestUtil.getInteger(request, "accessRoleLevel");

		if (StringUtils.isBlank(name) || parentId == null || sortOrderNo == null
				|| accessRoleLevel == null) {
			return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT,
					DwzJsonUtil.getErrorStatusMsg("有必填参数为空！"));
		}

		BackMenu menu = new BackMenu();
		menu.setId(id);
		menu.setName(name);
		menu.setParentId(parentId);
		menu.setSort(sortOrderNo.byteValue());
		menu.setUrl(url);
		menu.setRemark(remark);
		menu.setAccessRoleLevel(accessRoleLevel.byteValue());

		String uri = request.getRequestURI() + "?" + request.getQueryString();
		if (id == null) {
			systemService.addBackMenu(menu);
		} else {
			systemService.updateBackMenu(menu);
		}
		return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("保存成功！"));
	}

	@RequestMapping(params = "act=del")
	public ModelAndView deleteTreeNode(HttpServletRequest request) {
		List<Integer> menuIds = RequestUtil.getIntegers(request, "treecheckbox");
		if (null == menuIds || menuIds.isEmpty()) {
			return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT,
					DwzJsonUtil.getOkStatusMsg("没有选择要删除的菜单！"));
		}
		String uri = request.getRequestURI() + "?" + request.getQueryString();
		systemService.deleteBackMenusByIds(menuIds);
		return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("删除菜单成功！"));
	}

}
