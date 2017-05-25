package com.huyang.web.controller.login;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huyang.common.utils.CookieUtils;
import com.huyang.common.utils.IdsResult;
import com.huyang.common.utils.JedisClient;
import com.huyang.common.utils.RequestUtil;
import com.huyang.dao.po.BackMenu;
import com.huyang.dao.po.User;
import com.huyang.service.SystemService;
import com.huyang.service.UserService;
import com.huyang.web.Constants;
import com.huyang.web.controller.BaseController;
import com.huyang.web.controller.IndexController;

/**
 * @Project: ids-web
 * @Package com.huyang.web.controller.login
 * @author HuYang
 * @date 2017年4月23日 下午5:34:21
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserService userService;

	@Autowired
	private JedisClient jedisClient;

	@RequestMapping(value = { "/index.html", "", "/" })
	public String login(Model model) {
		/*
		 * List<BackMenu> menus =
		 * systemService.selectAllBackMenusWithLevelRelation();
		 * model.addAttribute("menus", menus);
		 */
		return "system/login/login";
	}

	/**
	 * 用户登录
	 * 
	 * @param loginUser
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/doLogin.html")
	public String login(User loginUser, HttpServletRequest request, HttpServletResponse response, Model model) {

		IdsResult IdsResult = userService.UserLogin(loginUser, request, response);

		if (IdsResult.getStatus() != 200) {
			model.addAttribute("msg", "用户名或者密码错误！");
			return "system/login/login";
		}
		User user = (User) IdsResult.getData();
		model.addAttribute("user", user);
		return "redirect:/index";
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logout.html")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		User loginUser = getLoginUser(request);
		String key = CookieUtils.getCookieValue(request, Constants.IDS_USER_TOKEN);
		jedisClient.del(Constants.IDS_USER_TOKEN + ":" + key);
		CookieUtils.deleteCookie(request, response, Constants.IDS_USER_TOKEN);
		if (loginUser != null) {
			logger.info("用户id：" + loginUser.getUid() + " 用户名：" + loginUser.getName() + " ,登出成功！");
		}
		response.sendRedirect(request.getAttribute("basePath") + "/login/index.html");
		return null;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @param response
	 * @param realName
	 * @param phone
	 * @return
	 */
	@RequestMapping("/saveInfo.html")
	@ResponseBody
	public IdsResult saveInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String email, @RequestParam(required = true) String tel) {

		User loginUser = getLoginUser(request);
		String token = CookieUtils.getCookieValue(request, Constants.IDS_USER_TOKEN);
		loginUser.setTel(tel);
		loginUser.setEmail(email);
		loginUser.setPassword(userService.findUserById(loginUser.getUid()).getPassword());
		IdsResult idsResult = userService.upDateUser(loginUser, token);
		return idsResult;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param response
	 * @param originPass
	 * @param newPass
	 * @param newPassConfirm
	 * @return
	 */
	@RequestMapping("/resetPass.html")
	@ResponseBody
	public IdsResult resetPass(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String originPass, @RequestParam(required = true) String newPass,
			@RequestParam(required = true) String newPassConfirm) {


		User loginUser = getLoginUser(request);
		if (loginUser == null) {
			return IdsResult.build(400, "请先登录");
		}

		if (StringUtils.isBlank(originPass) || StringUtils.isBlank(newPass) || StringUtils.isBlank(newPassConfirm)) {
			return IdsResult.build(400, "输入不完整");
		}

		if (!newPass.equals(newPassConfirm)) {
			return IdsResult.build(400, "两次密码输入不一致！");
		}

		IdsResult idsResult = userService.resetPass(loginUser,originPass, newPass);

		return idsResult;
	}
}
