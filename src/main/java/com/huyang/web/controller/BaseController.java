package com.huyang.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.huyang.common.utils.CookieUtils;
import com.huyang.common.utils.JedisClient;
import com.huyang.common.utils.JsonUtils;
import com.huyang.dao.po.User;
import com.huyang.web.Constants;
import com.huyang.web.RedisKey;

/**
 * BaseController 基础控制器
 *
 * @version V1.0.0
 * @author <a href="chufeng.xu@downjoy.com">徐楚风</a>
 * @since 2017-02-09 17:36
 */
@Controller
public class BaseController implements Constants, RedisKey {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Resource
	private JedisClient jedisClient;

	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception e) {
		logger.info("[URL:]" + request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + request.getRequestURI() + "?"
				+ request.getQueryString());
		logger.info("[ERROR:]" + e.getMessage(), e);

		String contentType = request.getHeader("Content-Type");

		if (StringUtils.isNotBlank(contentType) && contentType.contains("json")) {
			return JSON.toJSONString("");
		}
		request.setAttribute("msg", e.getMessage());
		return "error";
	}

	/**
	 * getLoginUser 获得当前登录的用户实体
	 *
	 * @param
	 * @return com.fx.rh.to.connect.AdminTO
	 *
	 * @version V1.0.0
	 * @author <a href="chufeng.xu@downjoy.com">徐楚风</a>
	 * @since 2017-02-16 13:26
	 */
	protected User getLoginUser(HttpServletRequest request) {
		String key = CookieUtils.getCookieValue(request, Constants.IDS_USER_TOKEN);
		//String key = (String) request.getSession().getAttribute(Constants.IDS_USER_TOKEN);
		if (key == null) {
			return null;
		}
		String object = jedisClient.get(Constants.IDS_USER_TOKEN + ":" + key);
		if (object == null) {
			return null;
		}
		return JsonUtils.jsonToPojo(object, User.class);
	}

}
