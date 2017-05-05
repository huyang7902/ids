package com.huyang.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.dao.mapper.ClassMapper;
import com.huyang.dao.mapper.CollegeMapper;
import com.huyang.dao.mapper.CoursesMapper;
import com.huyang.dao.mapper.ProfessionMapper;
import com.huyang.dao.po.Class;
import com.huyang.dao.po.ClassExample;
import com.huyang.dao.po.College;
import com.huyang.dao.po.CollegeExample;
import com.huyang.dao.po.CollegeExample.Criteria;
import com.huyang.dao.po.Courses;
import com.huyang.dao.po.CoursesExample;
import com.huyang.dao.po.Profession;
import com.huyang.dao.po.ProfessionExample;
import com.huyang.service.system.CollegeAndProfessionAndClassService;

/**
 * 学院、专业、班级基本操作接口实现类
 *
 * @Project ids
 * @package com.huyang.service.impl
 * @author HuYang 790247179@qq.com
 * @date 2017年5月4日 下午2:22:41
 * @version V1.0 Copyright (c) 2017
 */
@Service
public class CollegeAndProfessionAndClassServiceImpl implements CollegeAndProfessionAndClassService {

	private static final Logger logger = LoggerFactory.getLogger(CollegeAndProfessionAndClassServiceImpl.class);
	
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private ProfessionMapper professionMapper;
	@Autowired
	private ClassMapper classMapper;
	@Autowired
	private CoursesMapper coursesMapper;
	
	@Override
	public College findCollegeByName(String collegeName) {
		CollegeExample example = new CollegeExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollegeNameEqualTo(collegeName);
		List<College> list = collegeMapper.selectByExample(example);
		
		if (list == null || list.size() <1) {
			logger.warn("学院名："+collegeName + "没有找到");
			return new College();
		}
		
		College college = list.get(0);
		return college;
	}

	@Override
	public Profession findProfessionByName(String professionName) {
		ProfessionExample example = new ProfessionExample();
		com.huyang.dao.po.ProfessionExample.Criteria criteria = example.createCriteria();
		criteria.andProfessionNameEqualTo(professionName);
		List<Profession> list = professionMapper.selectByExample(example);
		
		if (list == null || list.size() <1) {
			logger.warn("专业名："+professionName + "没有找到");
			return null;
		}
		Profession profession = list.get(0);
		return profession;
	}

	@Override
	public Class findClassByName(String className) {
		ClassExample example = new ClassExample();
		com.huyang.dao.po.ClassExample.Criteria criteria = example.createCriteria();
		criteria.andClassNameEqualTo(className);
		List<Class> list = classMapper.selectByExample(example);
		
		if (list == null || list.size() <1) {
			logger.warn("班级名："+ className + "没有找到");
			return null;
		}
		Class class1 = list.get(0);
		return class1;
	}

	@Override
	public College findCollegeById(String collegeId) {
		College college = collegeMapper.selectByPrimaryKey(collegeId);
		return college;
	}

	@Override
	public Profession findProfessionById(String professionId) {
		Profession profession = professionMapper.selectByPrimaryKey(professionId);
		return profession;
	}

	@Override
	public Class findClassById(String classId) {
		ClassExample example = new ClassExample();
		com.huyang.dao.po.ClassExample.Criteria criteria = example.createCriteria();
		criteria.andClassIdEqualTo(classId);
		List<Class> list = classMapper.selectByExample(example);
		if (list == null || list.size() <1) {
			logger.warn("班级id："+ classId + "没有找到");
			return null;
		}
		Class class1 = list.get(0);
		return class1;
	}

	@Override
	public List<Profession> findProfessionBycollegeId(String collegeId) {
		ProfessionExample example = new ProfessionExample();
		com.huyang.dao.po.ProfessionExample.Criteria criteria = example.createCriteria();
		criteria.andCollegeIdEqualTo(collegeId);
		List<Profession> professionList = professionMapper.selectByExample(example);
		return professionList;
	}

	@Override
	public List<Class> findClassByprofessionId(String professionId) {
		ClassExample example = new ClassExample();
		com.huyang.dao.po.ClassExample.Criteria criteria = example.createCriteria();
		criteria.andProIdEqualTo(professionId);
		
		List<Class> classList = classMapper.selectByExample(example);
		return classList;
	}

	@Override
	public List<Class> findGradeByProfessionId(String professionId) {
		List<Class> gradeList = classMapper.findClassByprofessionId(professionId);
		
		return gradeList;
	}

	@Override
	public List<Class> findClassByGradeAndProfessionId(String professionId, String grade) {
		ClassExample example = new ClassExample();
		com.huyang.dao.po.ClassExample.Criteria criteria = example.createCriteria();
		criteria.andProIdEqualTo(professionId);
		criteria.andGradeEqualTo(grade);
		List<Class> classList = classMapper.selectByExample(example);
		return classList;
	}

	@Override
	public List<Courses> findClassByGradeAndClassId(String professionId, String grade, String classId) {
		CoursesExample example = new CoursesExample();
		com.huyang.dao.po.CoursesExample.Criteria criteria = example.createCriteria();
		criteria.andClassIdEqualTo(classId);
		criteria.andGradeEqualTo(grade);
		criteria.andProIdEqualTo(professionId);
		List<Courses> courseslist = coursesMapper.selectByExample(example);
		return courseslist;
	}

	@Override
	public Courses findCoursesDetail(String professionId, String grade, String classId, Long courseId) {
		CoursesExample example = new CoursesExample();
		com.huyang.dao.po.CoursesExample.Criteria criteria = example.createCriteria();
		criteria.andProIdEqualTo(professionId);
		criteria.andGradeEqualTo(grade);
		criteria.andClassIdEqualTo(classId);
		criteria.andCourseIdEqualTo(courseId);
		List<Courses> list = coursesMapper.selectByExample(example);
		
		if (list == null || list.size() <1) {
			logger.warn("professionId："+ professionId +", grade:" + grade +", classId: "+classId +", courseName:"+courseId+ "对应的课程详细信息没有找到");
			return null;
		}
		Courses courseDetail = list.get(0);
		return courseDetail;
	}




}
