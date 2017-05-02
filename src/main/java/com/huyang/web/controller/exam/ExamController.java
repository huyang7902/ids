package com.huyang.web.controller.exam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 监考页面控制类
 * 
 * @Project: ids-web
 * @Package com.huyang.web.controller.exam
 * @author HuYang
 * @date 2017年4月24日 下午9:05:30
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Controller
@RequestMapping("/jiankao/jiankao-list.html")
public class ExamController {

	@RequestMapping(params = "act=list")
	public String jiankaoPage(@RequestParam(defaultValue = "") String college,
			@RequestParam(defaultValue = "") String major, Model model) {
		model.addAttribute("college", college);
		model.addAttribute("major", major);
		return "jiankao/jiankao-list";
	}

	@RequestMapping(params = "act=add")
	public String jiankaoAdd(@RequestParam(defaultValue = "") String college,
			@RequestParam(defaultValue = "") String major, Model model) {
		model.addAttribute("college", college);
		model.addAttribute("major", major);
		return "jiankao/jiankao-add";
	}

}
