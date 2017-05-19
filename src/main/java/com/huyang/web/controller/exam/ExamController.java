package com.huyang.web.controller.exam;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.huyang.common.utils.RequestUtil;
import com.huyang.dao.mapper.ExamMapper;
import com.huyang.dao.po.Class;
import com.huyang.dao.po.College;
import com.huyang.dao.po.Courses;
import com.huyang.dao.po.CoursesExt;
import com.huyang.dao.po.Exam;
import com.huyang.dao.po.Profession;
import com.huyang.dao.po.User;
import com.huyang.service.CollegeAndProfessionAndClassService;
import com.huyang.service.CommonExamService;
import com.huyang.service.ExamAdminService;
import com.huyang.service.UserService;
import com.huyang.web.controller.BaseController;

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
public class ExamController extends BaseController {

	@Autowired
	private CollegeAndProfessionAndClassService collegeAndProfessionAndClassService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonExamService commonExamService;
	@Autowired
	private ExamAdminService examAdminService;

	/**
	 * 监考列表页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "act=list")
	public String jiankaoPage(HttpServletRequest request, HttpServletResponse response,
			Model model) {

		// 获取学院名
		String collegeName = null;
		// 获取专业名
		String professionName = null;
		try {
			collegeName = new String(
					RequestUtil.getString(request, "college").getBytes("ISO-8859-1"), "UTF-8");
			professionName = new String(
					RequestUtil.getString(request, "profession").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		College college = collegeAndProfessionAndClassService.findCollegeByName(collegeName);
		Profession profession = collegeAndProfessionAndClassService
				.findProfessionByName(professionName);

		List<Class> gradeList = collegeAndProfessionAndClassService
				.findGradeByProfessionId(profession.getProfessionId());

		// 查询监考列表
		// 获取学院id
		String collegeId = college.getCollegeId();
		// 获取专业id
		String proId = profession.getProfessionId();
		// 获取年级
		String grade = RequestUtil.getString(request, "grade");
		// 获取班级
		String classId = RequestUtil.getString(request, "classId");
		// 获取日期范围
		String startTime = RequestUtil.getString(request, "startTime");
		String endTime = RequestUtil.getString(request, "endTime");
		//获取课程名
		String name = RequestUtil.getString(request, "name");
		// 获取课程id
		String courseId = RequestUtil.getString(request, "courseId");
		Exam exam = new Exam();
		if (StringUtils.isBlank(collegeId)) {
			exam.setCollegeId("");
		}else {
			exam.setCollegeId(collegeId);
		}
		if (StringUtils.isBlank(proId)) {
			
			exam.setProId("");
		} else {
			exam.setProId(proId);
		}
		if (StringUtils.isBlank(grade)) {
			exam.setGrade("");
		} else {
			exam.setGrade(grade);
		}
		if (StringUtils.isBlank(classId)) {
			exam.setClassId("");
		} else {
			exam.setClassId(classId);
		}
		if (StringUtils.isBlank(courseId)) {
			exam.setCourseId("");
		} else {
			exam.setCourseId(courseId);
		}
		if (StringUtils.isBlank(name)) {
			exam.setCourseId("");
		} else {
			exam.setName(name);
		}

		// 获取分页
		Integer pageNum = RequestUtil.getInteger(request, "pageNum");
		Integer pageSize = RequestUtil.getInteger(request, "pageSize");
		// 查询
		List<Exam> list = commonExamService.getListExam(exam, startTime, endTime, pageNum,
				pageSize);

		PageInfo<Exam> examList = new PageInfo<>(list);

		// 取出监考人员
		if (list != null) {

			for (Exam exam2 : list) {

				List<String> peopleList = new ArrayList<>();
				if (StringUtils.isNotBlank(exam2.getPeopleName())) {
					String[] peopleName = exam2.getPeopleName().split("#");
					for (String name1 : peopleName) {
						peopleList.add(name1);
					}
					if (exam2.getPeopleNum() != peopleList.size()) {
						int peopleListNum = peopleList.size();
						for (int i = 0; i < exam2.getPeopleNum() - peopleListNum; i++) {
							peopleList.add("空缺");
						}
					}
				} else {
					for (int i = 0; i < exam2.getPeopleNum(); i++) {
						peopleList.add("空缺");
					}
				}
				exam2.setPeopleList(peopleList);
				
				User teacher = userService.findUserById(exam2.getTeacherId());
				exam2.setTeacherName(teacher.getName());
			}
			
			
		}
		

		User loginUser = getLoginUser(request);
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("college", college);
		model.addAttribute("profession", profession);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("examList", examList);
		return "jiankao/jiankao-list";
	}

	/**
	 * 跳转添加监考页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "act=add")
	public String jiankaoAdd(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String collegeId = RequestUtil.getString(request, "collegeId");
		String professionId = RequestUtil.getString(request, "professionId");

		College college = collegeAndProfessionAndClassService.findCollegeById(collegeId);
		Profession profession = collegeAndProfessionAndClassService
				.findProfessionById(professionId);
		List<Class> gradeList = collegeAndProfessionAndClassService
				.findGradeByProfessionId(professionId);

		model.addAttribute("college", college);
		model.addAttribute("profession", profession);
		model.addAttribute("gradeList", gradeList);

		return "jiankao/jiankao-add";
	}
	
	@RequestMapping(params = "act=edit")
	public String editExam(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String examId = RequestUtil.getString(request, "examId");
		
		Exam exam = examAdminService.editExamById(examId);
		College college = collegeAndProfessionAndClassService.findCollegeById(exam.getCollegeId());
		Profession profession = collegeAndProfessionAndClassService.findProfessionById(exam.getProId());
		Class class1 = collegeAndProfessionAndClassService.findClassByClassId(exam.getClassId());
		User teacher = userService.findUserById(exam.getTeacherId());
		List<String> peopleList = new ArrayList<>();
		
		if (StringUtils.isNotBlank(exam.getPeopleName())) {
			String[] peopleName = exam.getPeopleName().split("#");
			for (String name : peopleName) {
				peopleList.add(name);
			}
			if (exam.getPeopleNum() != peopleList.size()) {
				int peopleListNum = peopleList.size();
				for (int i = 0; i < exam.getPeopleNum() - peopleListNum; i++) {
					peopleList.add("空缺");
				}
			}
		} else {
			for (int i = 0; i < exam.getPeopleNum(); i++) {
				peopleList.add("空缺");
			}
		}
		exam.setPeopleList(peopleList);
		
		model.addAttribute("exam", exam);
		model.addAttribute("college", college);
		model.addAttribute("profession", profession);
		model.addAttribute("class1", class1);
		model.addAttribute("teacher", teacher);
		model.addAttribute("peopleList", peopleList);
		
		return "jiankao/jiankao-edit";
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
	public List<Class> ajaxFindClassByGrade(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String professionId = RequestUtil.getString(request, "professionId");
		String grade = RequestUtil.getString(request, "grade");
		List<Class> classList = collegeAndProfessionAndClassService
				.findClassByGradeAndProfessionId(professionId, grade);
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
	public List<Courses> ajaxFindCoursesByClass(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String professionId = RequestUtil.getString(request, "professionId");
		String grade = RequestUtil.getString(request, "grade");
		String ClassId = RequestUtil.getString(request, "classId");
		List<Courses> coursesList = collegeAndProfessionAndClassService
				.findClassByGradeAndClassId(professionId, grade, ClassId);
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
	public CoursesExt ajaxFindCoursesDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String professionId = RequestUtil.getString(request, "professionId");
		String grade = RequestUtil.getString(request, "grade");
		String ClassId = RequestUtil.getString(request, "classId");
		String coursesName = RequestUtil.getString(request, "coursesName");
		Courses courses = collegeAndProfessionAndClassService.findCoursesDetail(professionId, grade,
				ClassId, coursesName);
		User user = userService.findUserById(courses.getUserId());
		user.setPassword(null);
		CoursesExt coursesExt = new CoursesExt();
		coursesExt.setCourses(courses);
		coursesExt.setUser(user);

		return coursesExt;
	}
	
}
