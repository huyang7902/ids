package com.huyang.web.controller.exam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huyang.common.utils.IdsResult;
import com.huyang.common.utils.RequestUtil;
import com.huyang.dao.po.Class;
import com.huyang.dao.po.Exam;
import com.huyang.service.CollegeAndProfessionAndClassService;
import com.huyang.service.ExamAdminService;
import com.huyang.web.Constants;
import com.huyang.web.controller.BaseController;
import com.huyang.web.controller.login.LoginController;
import com.mysql.fabric.xmlrpc.base.Data;

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
public class ExamAdminController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ExamAdminController.class);

	@Autowired
	private ExamAdminService examAdminService;
	
	@Autowired
	private CollegeAndProfessionAndClassService collegeAndProfessionAndClassService;
	
	/**
	 * 添加监考类
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "act=add")
	@ResponseBody
	public IdsResult add(HttpServletRequest request, HttpServletResponse response, Model model) {
		String collegeId = RequestUtil.getString(request, "collegeId");
		String proId = RequestUtil.getString(request, "proId");
		String grade = RequestUtil.getString(request, "grade");
		String classId = RequestUtil.getString(request, "classId");
		String courseId = RequestUtil.getString(request, "courseId");
		String lessonNumber = RequestUtil.getString(request, "lessonNumber");
		String name = RequestUtil.getString(request, "courseName");
		String teacherId = RequestUtil.getString(request, "teacherId");
		String classRoom = RequestUtil.getString(request, "classRoom");
		Integer peopleNum = RequestUtil.getInteger(request, "peopleNum");
		String startTime = RequestUtil.getString(request, "startTime");
		String deadTime = RequestUtil.getString(request, "deadTime");
		String remark = RequestUtil.getString(request, "remark");
		Exam exam = new Exam();
		exam.setId(courseId + lessonNumber);
		exam.setCollegeId(collegeId);
		exam.setProId(proId);
		exam.setGrade(grade);
		exam.setClassId(classId);
		exam.setCourseId(courseId);
		exam.setLessonNumber(lessonNumber);
		exam.setName(name);
		exam.setTeacherId(teacherId);
		exam.setClassRoom(classRoom);
		exam.setPeopleNum(peopleNum);
		//查询班级名
		Class findClassById = collegeAndProfessionAndClassService.findClassById(classId);
		exam.setClassName(findClassById.getClassName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			exam.setStartTime(sdf.parse(startTime));
			exam.setDeadTime(sdf.parse(deadTime));
		} catch (ParseException e) {
			logger.warn("添加监考日期格式转换失败！");
			e.printStackTrace();
			return IdsResult.ok("日期不正确！");
		}
		exam.setCreatTime(new Date());
		exam.setCreatPeopleId(getLoginUser(request).getUid());
		exam.setStatus(Constants.EXAM_STATUS_1);
		exam.setRemark(remark);
		IdsResult idsResult = examAdminService.addExam(exam);
		return idsResult;
	}

}
