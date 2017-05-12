package com.huyang.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.huyang.common.utils.IdsResult;
import com.huyang.common.utils.MapUtils;
import com.huyang.dao.mapper.ExamMapper;
import com.huyang.dao.mapper.UserMapper;
import com.huyang.dao.po.Exam;
import com.huyang.dao.po.ExamExample;
import com.huyang.dao.po.ExamExample.Criteria;
import com.huyang.dao.po.User;
import com.huyang.dao.po.UserExample;

/**
 * 定时监考任务类
 *
 * @Project ids
 * @package com.huyang.schedule
 * @author HuYang 790247179@qq.com
 * @date 2017年5月10日 下午7:37:50
 * @version V1.0 Copyright (c) 2017
 */
public class IdsExamJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(IdsExamJob.class);

	@Override
	protected void executeInternal(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		// 获取spring上下文
		ApplicationContext applicationContext = (ApplicationContext) paramJobExecutionContext.getJobDetail()
				.getJobDataMap().get("applicationContext");
		// 获取ExamMapper
		ExamMapper examMapper = applicationContext.getBean(ExamMapper.class);
		UserMapper userMapper = applicationContext.getBean(UserMapper.class);
		ExamExample example = new ExamExample();
		Criteria criteria = example.createCriteria();
		// 查询status为1的监考
		criteria.andStatusEqualTo((byte) 1);
		// 获取当前时间
		Date nowTime = new Date();
		// 获取日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowTime);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date nowTimeBefore = calendar.getTime();
		System.out.println("nowTime: " + nowTime);
		System.out.println("nowTime-1:" + calendar.getTime());
		criteria.andDeadTimeBetween(nowTimeBefore, nowTime);
		List<Exam> examList = examMapper.selectByExample(example);

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

		// 循环遍历要排序的监考
		for (Exam exam : examList) {
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
				}
				for (String people : list) {
					peopleNames += ("#" + people);
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
				UserExample example1 = new UserExample();
				com.huyang.dao.po.UserExample.Criteria criteria1 = example1.createCriteria();
				criteria1.andNameEqualTo(peopleName);
				List<User> selectByExample = userMapper.selectByExample(example1);
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
				logger.warn(data);
			}
		}
	}
}
