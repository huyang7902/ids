package com.huyang.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.huyang.dao.po.BackMenu;
import com.huyang.dao.po.BackMenuExample;
import com.huyang.dao.po.User;

public interface BackMenuMapper {
	int countByExample(BackMenuExample example);

	int deleteByExample(BackMenuExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(BackMenu record);

	int insertSelective(BackMenu record);

	List<BackMenu> selectByExample(BackMenuExample example);

	List<BackMenu> selectAllBackMenus();

	/**
	 * 带有层级关系的菜单
	 * 
	 * @return
	 */
	List<BackMenu> selectAllBackMenusWithLevelRelation();

	/**
	 * 根据角色查询菜单
	 * 
	 * @param accessRoleLevele
	 * @return
	 */
	List<BackMenu> selectRoleBackMenusWithLevelRelation(User user);

	BackMenu selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") BackMenu record,
			@Param("example") BackMenuExample example);

	int updateByExample(@Param("record") BackMenu record,
			@Param("example") BackMenuExample example);

	int updateByPrimaryKeySelective(BackMenu record);

	int updateByPrimaryKey(BackMenu record);

	int batchDeleteMenusByIds(List<Integer> ids);
}