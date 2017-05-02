package com.huyang.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyang.common.type.UserType;
import com.huyang.common.utils.JedisClient;
import com.huyang.common.utils.JsonUtils;
import com.huyang.dao.po.BackMenu;
import com.huyang.dao.po.User;
import com.huyang.service.system.SystemService;
import com.huyang.web.Constants;

/**
 * 首页
 * 
 * @Project: ids
 * @Package com.huyang.web.controller
 * @author HuYang
 * @date 2017年4月30日 下午1:08:23
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Controller
public class IndexController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Resource
	private SystemService systemService;
	@Resource
	private JedisClient jedisClient;

	@RequestMapping({"/index","/"})
    public String defaultIndex(HttpServletRequest request,
                               HttpServletResponse response, Model model) {

        User loginUser = getLoginUser(request);

        List<BackMenu> menus = null;

        /**
         * 根据用户角色查询菜单
         */
        if(UserType.COMMON_ADMIN.equals(loginUser.getAccessRoleLevele().toString()) || UserType.SUPER_ADMIN.equals(loginUser.getAccessRoleLevele().toString())){
            menus= systemService.selectAllBackMenusWithLevelRelation();
            jedisClient.set(Constants.IDS_ADMIN_MENU,JsonUtils.objectToJson(menus));
        }else{
            menus= systemService.selectRoleBackMenusWithLevelRelation(loginUser);
            jedisClient.set(Constants.IDS_USER_MENU,JsonUtils.objectToJson(menus));
        }


        model.addAttribute("user",loginUser);
        model.addAttribute("menus",menus);
        return "index";
    }
}
