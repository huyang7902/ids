package com.huyang.service.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.common.utils.IdsResult;
import com.huyang.dao.mapper.ExamMapper;
import com.huyang.dao.po.Exam;
import com.huyang.dao.po.User;
import com.huyang.service.ExamUserService;

/**
 * 用户监考操作实现类
 *
 * @Project ids
 * @package com.huyang.service.impl
 * @author HuYang 790247179@qq.com
 * @date 2017年5月9日 下午3:26:25
 * @version V1.0 Copyright (c) 2017
 */
@Service
public class ExamUserServiceImpl implements ExamUserService {

	@Autowired
	private ExamMapper examMapper;
	
	@Override
	public IdsResult addExamUser(String examId, User loginUser) {
		// 根据examId查询监考数据
		Exam exam = examMapper.selectByPrimaryKey(examId);
		// 判断人数是否已满
		String peopleName = exam.getPeopleName();
		if (StringUtils.isNotBlank(peopleName)) {
			String[] peoples = peopleName.split("#");
			if (exam.getPeopleNum() == peoples.length) {
				return IdsResult.build(400, "人数已满！请选择其他监考课程");
			}
			// 判断是否是自己选择
			for (String people : peoples) {
				if (people.equals(loginUser.getName())) {
					return IdsResult.build(400, "您已经选择该门监考！");
				}
			}
			
			// 添加
			peopleName += ("#" +loginUser.getName());
		}else {
			peopleName = loginUser.getName();
			
		}
		
		exam.setPeopleName(peopleName);
		int i = examMapper.updateByPrimaryKey(exam);
		if (i != 1) {
			return IdsResult.build(400, "数据库异常");
		}
		
		
		return IdsResult.build(200, "您成功选择该门监考课程！");
	}

}
