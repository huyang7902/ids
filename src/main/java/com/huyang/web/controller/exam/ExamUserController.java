package com.huyang.web.controller.exam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huyang.common.utils.IdsResult;
import com.huyang.common.utils.RequestUtil;
import com.huyang.dao.po.User;
import com.huyang.service.ExamUserService;
import com.huyang.web.controller.BaseController;
import com.sun.tools.internal.ws.processor.model.Request;
/**
 * 用户监考操作controller
 *
 * @Project ids
 * @package com.huyang.web.controller.exam
 * @author HuYang 790247179@qq.com
 * @date 2017年5月9日 下午2:54:00
 * @version V1.0 Copyright (c) 2017
 */
@Controller
@RequestMapping("/jiankao/jiankao-user.html")
public class ExamUserController extends BaseController{

	@Autowired
	private ExamUserService examUserService;
	
	@RequestMapping(params = "act=choose")
	@ResponseBody
	public IdsResult chooseExam(HttpServletRequest request, HttpServletResponse response) {
		// 获得选择的监考id
		String examId = RequestUtil.getString(request, "id");
		// 得到当前用户
		User loginUser = getLoginUser(request);
		
		//进行添加监考用户操作
		IdsResult idsResult = examUserService.addExamUser(examId, loginUser);
		
		
		return idsResult;
	}
}
