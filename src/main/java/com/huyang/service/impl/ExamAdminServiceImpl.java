package com.huyang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.common.utils.IdsResult;
import com.huyang.dao.mapper.ExamMapper;
import com.huyang.dao.po.Exam;
import com.huyang.service.ExamAdminService;

/**
 * 监考管理员service实现类
 * 
 * @Project: ids
 * @Package com.huyang.service.impl
 * @author HuYang
 * @date 2017年5月6日 下午11:14:37
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Service
public class ExamAdminServiceImpl implements ExamAdminService {

	@Autowired
	private ExamMapper examMapper;

	@Override
	public IdsResult addExam(Exam exam) {
		int i = 1;
		try {
			i = examMapper.insert(exam);
		} catch (Exception e) {
			return IdsResult.build(500, "重复添加");
		}
		if (i != 1) {
			return IdsResult.build(500, "添加失败！");
		}
		return IdsResult.ok("添加成功！");
	}

}
