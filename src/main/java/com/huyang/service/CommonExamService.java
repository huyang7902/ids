package com.huyang.service;

import java.util.List;

import com.huyang.dao.po.Exam;

/**
 * 监考查询通用接口
 * 
 * @Project: ids
 * @Package com.huyang.service
 * @author HuYang
 * @date 2017年5月7日 下午1:42:34
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public interface CommonExamService {

	/**
	 * 根据条件查询监考列表
	 * @param exam
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Exam> getListExam(Exam exam,String startTime, String endTime, Integer pageNum, Integer pageSize);
}
