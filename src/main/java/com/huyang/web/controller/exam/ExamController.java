package com.huyang.web.controller.exam;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huyang.common.utils.RequestUtil;
import com.huyang.dao.po.Class;
import com.huyang.dao.po.College;
import com.huyang.dao.po.Courses;
import com.huyang.dao.po.Profession;
import com.huyang.service.system.CollegeAndProfessionAndClassService;

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

	@Autowired
	private CollegeAndProfessionAndClassService collegeAndProfessionAndClassService;

	@RequestMapping(params = "act=list")
	public String jiankaoPage(HttpServletRequest request, HttpServletResponse response, Model model) {

		String collegeName = null;
		String professionName = null;
		try {
			collegeName = new String(RequestUtil.getString(request, "college").getBytes("ISO-8859-1"), "UTF-8");
			professionName = new String(RequestUtil.getString(request, "profession").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		College college = collegeAndProfessionAndClassService.findCollegeByName(collegeName);
		Profession profession = collegeAndProfessionAndClassService.findProfessionByName(professionName);

		List<Class> gradeList = collegeAndProfessionAndClassService
				.findGradeByProfessionId(profession.getProfessionId());

		model.addAttribute("college", college);
		model.addAttribute("profession", profession);
		model.addAttribute("gradeList", gradeList);
		return "jiankao/jiankao-list";
	}

	/**
	 * 添加监考
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "act=add")
	public String jiankaoAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
		String collegeId = RequestUtil.getString(request, "collegeId");
		String professionId = RequestUtil.getString(request, "professionId");

		College college = collegeAndProfessionAndClassService.findCollegeById(collegeId);
		Profession profession = collegeAndProfessionAndClassService.findProfessionById(professionId);
		List<Class> gradeList = collegeAndProfessionAndClassService.findGradeByProfessionId(professionId);

		model.addAttribute("college", college);
		model.addAttribute("profession", profession);
		model.addAttribute("gradeList", gradeList);

		return "jiankao/jiankao-add";
	}

	/**
	 * 根据年级，专业查找班级
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "act=getClass")
	@ResponseBody
	public List<Class> ajaxFindClassByGrade(HttpServletRequest request, HttpServletResponse response, Model model) {
		String professionId = RequestUtil.getString(request, "professionId");
		String grade = RequestUtil.getString(request, "grade");
		List<Class> classList = collegeAndProfessionAndClassService.findClassByGradeAndProfessionId(professionId, grade);
		return classList;
	}
	/**
	 * 根据年级，专业、班级查找课程
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "act=getCourses")
	@ResponseBody
	public List<Courses> ajaxFindCoursesByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
		String professionId = RequestUtil.getString(request, "professionId");
		String grade = RequestUtil.getString(request, "grade");
		String ClassId = RequestUtil.getString(request, "classId");
		List<Courses> coursesList = collegeAndProfessionAndClassService.findClassByGradeAndClassId(professionId, grade, ClassId);
		return coursesList;
	}
	/**
	 * 根据年级，专业、班级查找课程
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "act=getCourseDetail")
	@ResponseBody
	public Courses ajaxFindCoursesDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
		String professionId = RequestUtil.getString(request, "professionId");
		String grade = RequestUtil.getString(request, "grade");
		String ClassId = RequestUtil.getString(request, "classId");
		Long courseId = RequestUtil.getLong(request, "courseId");
		Courses course = collegeAndProfessionAndClassService.findCoursesDetail(professionId, grade, ClassId, courseId);
		return course;
	}

}
