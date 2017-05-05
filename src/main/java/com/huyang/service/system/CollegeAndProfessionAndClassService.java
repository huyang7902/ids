package com.huyang.service.system;

import java.util.List;

import com.huyang.dao.po.Class;
import com.huyang.dao.po.College;
import com.huyang.dao.po.Courses;
import com.huyang.dao.po.Profession;

/**
 * 学院、专业、班级基本操作接口
 *
 * @Project ids
 * @package com.huyang.service.system
 * @author HuYang 790247179@qq.com
 * @date 2017年5月4日 下午2:16:07
 * @version V1.0 Copyright (c) 2017
 */
public interface CollegeAndProfessionAndClassService {

	/**
	 * 根据学院名查找学院
	 * 
	 * @return
	 */
	College findCollegeByName(String collegeName);

	/**
	 * 根据专业名查找专业
	 * 
	 * @return
	 */
	Profession findProfessionByName(String professionName);

	/**
	 * 根据班级名查找班级
	 * 
	 * @return
	 */
	Class findClassByName(String className);

	/**
	 * 根据学院id查找学院
	 * 
	 * @return
	 */
	College findCollegeById(String collegeId);

	/**
	 * 根据专业id查找专业
	 * 
	 * @return
	 */
	Profession findProfessionById(String professionId);

	/**
	 * 根据班级id查找班级
	 * 
	 * @return
	 */
	Class findClassById(String classId);

	/**
	 * 根据学院id查找专业
	 * 
	 * @return
	 */
	List<Profession> findProfessionBycollegeId(String collegeId);

	/**
	 * 根据专业id查找班级
	 * 
	 * @return
	 */
	List<Class> findClassByprofessionId(String professionId);

	/**
	 * 根据专业id查找不重复的年级
	 * 
	 * @param professionId
	 * @return
	 */
	List<Class> findGradeByProfessionId(String professionId);

	/**
	 * 根据年级和专业查找班级
	 * 
	 * @param professionId
	 * @param grade
	 * @return
	 */
	List<Class> findClassByGradeAndProfessionId(String professionId, String grade);

	/**
	 * 根据年级和专业和班级查找课程名
	 * 
	 * @param professionId
	 * @param grade
	 * @param classId
	 * @return
	 */
	List<Courses> findClassByGradeAndClassId(String professionId, String grade, String classId);

	/**
	 * 根据年级和专业和班级和课程名查找课程详细详细
	 * 
	 * @param professionId
	 * @param grade
	 * @param classId
	 * @param courseName
	 * @return
	 */
	Courses findCoursesDetail(String professionId, String grade, String classId, Long courseId);

}
