package com.huyang.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huyang.dao.mapper.ExamMapper;
import com.huyang.dao.po.Exam;
import com.huyang.dao.po.ExamExample;
import com.huyang.dao.po.ExamExample.Criteria;
import com.huyang.service.CommonExamService;
import com.huyang.service.ExamAdminService;
import com.huyang.web.controller.exam.ExamAdminController;

/**
 * 监考通用实现类
 * 
 * @Project: ids
 * @Package com.huyang.service.impl
 * @author HuYang
 * @date 2017年5月7日 下午1:45:50
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Service
public class CommonExamServiceImpl implements CommonExamService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExamServiceImpl.class);
	
	@Autowired
	private ExamMapper examMapper;
	
	@Override
	public List<Exam> getListExam(Exam exam,String startTime, String endTime, Integer pageNum, Integer pageSize) {
		ExamExample example = new ExamExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollegeIdEqualTo(exam.getCollegeId());
		criteria.andProIdEqualTo(exam.getProId());
		if (exam.getGrade() != null) {
			criteria.andGradeEqualTo(exam.getGrade());
		}
		if (exam.getClassId() != null) {
			criteria.andClassIdEqualTo(exam.getClassId());
		}
		if (exam.getClassId() != null) {
			criteria.andClassIdEqualTo(exam.getClassId());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime !=null && startTime != null) {
			try {
				criteria.andStartTimeBetween(sdf.parse(startTime), sdf.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.warn("时间转换失败");
				return null;
			}
		}else if (startTime !=null && startTime ==null) {
				try {
					criteria.andStartTimeGreaterThanOrEqualTo(sdf.parse(startTime));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.warn("时间转换失败");
					return null;
				}
		}else if (startTime ==null && startTime !=null) {
			try {
				criteria.andStartTimeLessThanOrEqualTo(sdf.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.warn("时间转换失败");
				return null;
			}
	}
		pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize < 1 ? 2 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
		
		List<Exam> examList = examMapper.selectByExample(example);
		return examList;
	}

}
