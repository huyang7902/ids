package com.huyang.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huyang.dao.po.BackMenu;
import com.huyang.dao.po.User;

/**
 * @Project: ids-service
 * @Package com.huyang.service.system
 * @author HuYang
 * @date 2017年4月23日 下午3:00:54
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public interface SystemService {


	/**
	 * 带有层级关系的菜单
	 * 
	 * @return
	 */
	List<BackMenu> selectAllBackMenusWithLevelRelation();
	
	/**
	 * 查询普通用户菜单
	 * @param accessRoleLevele
	 * @return
	 */
	List<BackMenu> selectRoleBackMenusWithLevelRelation(User user);
	
	
    public BackMenu getMenuById(Integer id);

	List<BackMenu> getAllBackMenusWithLevelRelation();

	BackMenu addBackMenu(BackMenu backMenu);

	int deleteBackMenusByIds(@Param("ids")  List<Integer> ids);

	int updateBackMenu(BackMenu menu);
}
