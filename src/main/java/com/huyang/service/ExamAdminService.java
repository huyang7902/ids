package com.huyang.service;

import com.huyang.common.utils.IdsResult;
import com.huyang.dao.po.Exam;

/**
 * 管理员的监考管理类
 * 
 * @Project: ids
 * @Package com.huyang.service
 * @author HuYang
 * @date 2017年5月6日 下午11:12:16
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public interface ExamAdminService {

	/**
	 * 添加监考
	 * 
	 * @param exam
	 * @return
	 */
	IdsResult addExam(Exam exam);
}
