package com.huyang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.common.utils.IdsResult;
import com.huyang.common.utils.MapUtils;
import com.huyang.dao.mapper.ExamMapper;
import com.huyang.dao.mapper.UserMapper;
import com.huyang.dao.po.Exam;
import com.huyang.dao.po.User;
import com.huyang.dao.po.UserExample;
import com.huyang.dao.po.UserExample.Criteria;
import com.huyang.schedule.IdsExamJob;
import com.huyang.schedule.MessageService;
import com.huyang.service.ExamAdminService;
import com.mysql.fabric.xmlrpc.base.Data;

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

	private static final Logger logger = LoggerFactory.getLogger(ExamAdminServiceImpl.class);

	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private UserMapper userMapper;

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

	@Override
	public Exam editExamById(String examId) {
		Exam exam = examMapper.selectByPrimaryKey(examId);
		return exam;
	}

	@Override
	public IdsResult upDateExam(Exam newExam) {
		int i = examMapper.updateByPrimaryKey(newExam);
		if (i != 1) {
			return IdsResult.build(400, "更新失败！");
		}
		return IdsResult.build(200, "更新成功！请重新打开此页面");
	}

	@Override
	public IdsResult deleteExamById(String id) {
		int i = examMapper.deleteByPrimaryKey(id);
		if (i != 1) {
			return IdsResult.build(400, "删除监考失败！");
		}
		return IdsResult.build(200, "删除成功！请重新打开此页面");
	}

	@Override
	public IdsResult autoExamById(String id) {

		// 根据id查询监考
		Exam exam = examMapper.selectByPrimaryKey(id);
		if (exam == null) {
			return IdsResult.build(400, "该监考课程不存在！");
		}
		if (exam.getStatus() == 0) {
			return IdsResult.build(400, "该课程已经自动分配或分配成功或者已经结束！");
		}

		// 查询所有监考的人员
		List<String> peopleNameList = examMapper.selectAllExamPeopleName();
		System.out.println(peopleNameList);
		Map<String, Integer> map = new HashMap<>();
		for (String peopleNames : peopleNameList) {
			if (StringUtils.isNotBlank(peopleNames)) {
				// 切割人员名字
				String[] peoples = peopleNames.split("#");
				for (String people : peoples) {
					if (map.containsKey(people)) {
						map.put(people, map.get(people) + 1);
					} else {
						map.put(people, 1);
					}
				}
			}
		}
		// 查询所有老师
		UserExample userExample = new UserExample();
		com.huyang.dao.po.UserExample.Criteria createCriteria = userExample.createCriteria();
		createCriteria.andAccessRoleLeveleEqualTo((byte) 1);
		List<User> userList = userMapper.selectByExample(userExample);
		// 循环遍历，把所有教师加入map统计
		for (User user : userList) {
			if (!map.containsKey(user.getName())) {
				map.put(user.getName(), 0);
			}
		}
		// 根据监考次数排序
		Map<String, Integer> sortMapByValue = MapUtils.sortByValue(map);
		/*
		 * for (Entry<String, Integer> entry : sortMapByValue.entrySet()) {
		 * System.out.println("key= " + entry.getKey() + " and value= " +
		 * entry.getValue()); }
		 */

		// 定义一个list，判断监考人员是否重复
		List<String> list = new ArrayList<>();

		// 取出监考人员
		String peopleNames = exam.getPeopleName();

		// 不为空
		if (StringUtils.isNotBlank(peopleNames)) {
			String[] peoples = peopleNames.split("#");
			// 人数不足
			if (exam.getPeopleNum() > peoples.length) {
				for (int i = 0; i < exam.getPeopleNum() - peoples.length; i++) {
					// 取监考次数最少的老师
					// 遍历map,防止重复添加
					for (Entry<String, Integer> entry : sortMapByValue.entrySet()) {
						if (!list.contains(entry.getKey())) {
							list.add(entry.getKey());
							sortMapByValue.put(entry.getKey(), entry.getValue() + 1);
							// 重新排序
							sortMapByValue = MapUtils.sortByValue(sortMapByValue);
							break;
						}
					}
				}
			for (String people : list) {
				peopleNames += ("#" + people);
				}
			}
			if (exam.getPeopleNum() == peoples.length) {
				for (String people : peoples) {
					list.add(people);
				}
			}
		} else {
			// 为空
			for (int i = 0; i < exam.getPeopleNum(); i++) {
				// 取监考次数最少的老师
				for (Entry<String, Integer> entry : sortMapByValue.entrySet()) {
					if (!list.contains(entry.getKey())) {
						list.add(entry.getKey());
						sortMapByValue.put(entry.getKey(), entry.getValue() + 1);
						// 重新排序
						sortMapByValue = MapUtils.sortByValue(sortMapByValue);
						break;
					}
				}
			}
			// 拼接字符串
			for (String people : list) {
				if (StringUtils.isBlank(peopleNames)) {
					peopleNames += people;
				} else {
					peopleNames += ("#" + people);
				}
			}
		}
		exam.setStatus((byte) 0);
		exam.setPeopleName(peopleNames);
		examMapper.updateByPrimaryKey(exam);
		StringBuffer sb = new StringBuffer();
		// 发送短信
		for (String peopleName : list) {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(peopleName);
			List<User> selectByExample = userMapper.selectByExample(example);
			if (selectByExample != null && selectByExample.size() > 0) {
				User user = selectByExample.get(0);
				IdsResult idsResult = MessageService.sendMessage(exam, user);
				if (idsResult.getStatus() == 400) {
					sb.append(user.getName() + " ");
				}
			}
		}
		logger.info("监考：" + exam.getId() + "完成手动选择自动分配！");
		String data;
		if (!StringUtils.isBlank(sb)) {
			data = sb.append("发送短信失败！").toString();
		}else{
			data = null;
		}
		return IdsResult.build(200, "自动分配成功，请刷新本页面！", data);
	}

}
