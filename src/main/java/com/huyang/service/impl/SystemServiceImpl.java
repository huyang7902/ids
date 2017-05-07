package com.huyang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huyang.dao.mapper.BackMenuMapper;
import com.huyang.dao.po.BackMenu;
import com.huyang.dao.po.User;
import com.huyang.service.SystemService;

/**
 * @Project: ids-service
 * @Package com.huyang.service.impl.system
 * @author HuYang
 * @date 2017年4月23日 下午3:05:07
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
@Service()
public class SystemServiceImpl implements SystemService {

	@Autowired
	private BackMenuMapper backMenuMapper;



	@Override
	public List<BackMenu> selectAllBackMenusWithLevelRelation() {
		List<BackMenu> menus = backMenuMapper.selectAllBackMenusWithLevelRelation();
		return menus;
	}

	@Override
	public List<BackMenu> selectRoleBackMenusWithLevelRelation(User user) {
		return backMenuMapper.selectRoleBackMenusWithLevelRelation(user);
	}
	@Override
	public BackMenu getMenuById(Integer id) {
		return backMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BackMenu> getAllBackMenusWithLevelRelation() {
		return backMenuMapper.selectAllBackMenusWithLevelRelation();
	}

	@Override
	public BackMenu addBackMenu(BackMenu backMenu) {
		if (backMenu.getParentId() == null) {
			backMenu.setParentId(-1);
		}
		backMenuMapper.insertSelective(backMenu);
		return backMenu;
	}

	@Override
	public int updateBackMenu(BackMenu backMenu) {
		BackMenu oldMenu = backMenuMapper.selectByPrimaryKey(backMenu.getId());
		if (oldMenu == null) {
			return 0;
		}
		backMenuMapper.updateByPrimaryKeySelective(backMenu);
		return 1;
	}

	@Override
	public int deleteBackMenusByIds(List<Integer> ids) {
		return backMenuMapper.batchDeleteMenusByIds(ids);
	}


}
