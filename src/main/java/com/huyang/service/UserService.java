package com.huyang.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huyang.common.utils.IdsResult;
import com.huyang.dao.po.User;

/**
 * 用户service层
 * 
 * @Project: ids
 * @Package com.huyang.service.system
 * @author HuYang
 * @date 2017年4月30日 下午1:59:34
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public interface UserService {

	/**
	 * 用户通用登录方法
	 * 
	 * @param loginUser
	 * @param request
	 * @param response
	 * @return
	 */
	IdsResult UserLogin(User loginUser, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 根据用户id查找
	 * 
	 * @param uid
	 * @return
	 */
	User findUserById(String uid);

	/**
	 * 修改用户信息(邮箱、电话)
	 * 
	 * @param user
	 * @return
	 */
	IdsResult upDateUser(User user, String token);

	/**
	 * 修改用户密码
	 * 
	 * @param loginUser
	 * @param originPass
	 * @param newPass
	 * @return
	 */
	IdsResult resetPass(User loginUser, String originPass, String newPass);
}