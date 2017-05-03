package com.huyang.web.controller.exam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huyang.common.utils.IdsResult;
import com.huyang.common.utils.RequestUtil;

/**
 * 监考 管理员操作类
 *
 * @Project ids
 * @package com.huyang.web.controller.exam
 * @author HuYang 790247179@qq.com
 * @date 2017年5月2日 下午2:14:32
 * @version V1.0 Copyright (c) 2017
 */
@RequestMapping("/jiankao/jiankao-admin.html")
@Controller
public class ExamAdminController {

	@RequestMapping(params = "act=add")
	@ResponseBody
	public IdsResult add(HttpServletRequest request,
            HttpServletResponse response, Model model) {
		String string = RequestUtil.getString(request, "username");
		System.out.println(string);
		System.out.println("11111111111111");
		return IdsResult.ok();
	}
}
