package com.huyang.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huyang.dao.mapper.ExamMapper;
import com.huyang.dao.po.Exam;
import com.huyang.dao.po.ExamExample;
import com.huyang.dao.po.ExamExample.Criteria;
import com.huyang.service.CommonExamService;

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
		if (!StringUtils.isBlank(exam.getGrade())) {
			criteria.andGradeEqualTo(exam.getGrade());
		}
		if (!StringUtils.isBlank(exam.getClassId())) {
			criteria.andClassIdEqualTo(exam.getClassId());
		}
		if (!StringUtils.isBlank(exam.getClassId())) {
			criteria.andClassIdEqualTo(exam.getClassId());
		}
		if (!StringUtils.isBlank(exam.getName())) {
			criteria.andNameLike(exam.getName());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
			try {
				criteria.andStartTimeBetween(sdf.parse(startTime), sdf.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.warn("时间转换失败");
				return null;
			}
		}else if (!StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
				try {
					System.out.println(sdf.parse(startTime));
					criteria.andStartTimeGreaterThanOrEqualTo(sdf.parse(startTime));
				} catch (ParseException e) {
					e.printStackTrace();
					logger.warn("时间转换失败");
					return null;
				}
		}else if (StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
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
		example.setOrderByClause("start_time");
		List<Exam> examList = examMapper.selectByExample(example);
		return examList;
	}

}
