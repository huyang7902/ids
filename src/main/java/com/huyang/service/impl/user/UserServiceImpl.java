package com.huyang.service.impl.user;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.huyang.common.utils.CookieUtils;
import com.huyang.common.utils.IdsResult;
import com.huyang.common.utils.JedisClient;
import com.huyang.common.utils.JsonUtils;
import com.huyang.dao.mapper.UserMapper;
import com.huyang.dao.po.User;
import com.huyang.dao.po.UserExample;
import com.huyang.dao.po.UserExample.Criteria;
import com.huyang.service.system.UserService;
import com.huyang.web.Constants;
import com.huyang.web.controller.login.LoginController;

/**
 * 用户实现类
 * 
 * @Project: ids
 * @Package com.huyang.service.impl.user
 * @author HuYang
 * @date 2017年4月30日 下午2:03:03
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	/**
	 * 用户通用登录方法
	 * 
	 * @param loginUser
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public IdsResult UserLogin(User loginUser, HttpServletRequest request,
			HttpServletResponse response) {

		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUidEqualTo(loginUser.getUid());
		criteria.andAccessRoleLeveleEqualTo(loginUser.getAccessRoleLevele());
		List<User> userList = userMapper.selectByExample(userExample);
		if (userList == null || userList.size() == 0) {
			logger.info("用户：" + loginUser.getUid() + "用户名或密码错误！");
			return IdsResult.build(400, "用户名或密码错误！");
		}
		User user = userList.get(0);
		if (!DigestUtils.md5DigestAsHex(loginUser.getPassword().getBytes()).equals(user.getPassword())) {
			logger.info("用户：" + loginUser.getUid() + "用户名或密码错误！");
			return IdsResult.build(400, "用户名或密码错误！");
		}
		// 生成token
		String token = UUID.randomUUID().toString();
		// 保存用户名密码之前清空密码
		user.setPassword(null);
		// 把用户信息写入redis
		jedisClient.set(Constants.IDS_USER_TOKEN + ":" + token, JsonUtils.objectToJson(user));
		// 设置session的过期时间
		jedisClient.expire(Constants.IDS_USER_TOKEN + ":" + token, Constants.expire);

		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效
		CookieUtils.setCookie(request, response, Constants.IDS_USER_TOKEN, token);
		logger.info("用户：" + loginUser.getName() + "登录成功！");
		return IdsResult.ok(user);
	}
//	public static void main(String[] args) {
//		String md5DigestAsHex = DigestUtils.md5DigestAsHex("123".getBytes());
//		System.out.println(md5DigestAsHex);
//	}

}
