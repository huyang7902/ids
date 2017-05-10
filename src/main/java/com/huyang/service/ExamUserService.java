package com.huyang.service;

import com.huyang.common.utils.IdsResult;
import com.huyang.dao.po.User;

/**
 * 用户监考操作接口
 *
 * @Project ids
 * @package com.huyang.service
 * @author HuYang 790247179@qq.com
 * @date 2017年5月9日 下午3:24:22
 * @version V1.0 Copyright (c) 2017
 */
public interface ExamUserService {

	IdsResult addExamUser(String examId, User loginUser);
}
