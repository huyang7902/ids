package com.huyang.dao.mapper;

import com.huyang.dao.po.Profession;
import com.huyang.dao.po.ProfessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfessionMapper {
    int countByExample(ProfessionExample example);

    int deleteByExample(ProfessionExample example);

    int deleteByPrimaryKey(String pid);

    int insert(Profession record);

    int insertSelective(Profession record);

    List<Profession> selectByExample(ProfessionExample example);

    Profession selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") Profession record, @Param("example") ProfessionExample example);

    int updateByExample(@Param("record") Profession record, @Param("example") ProfessionExample example);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);
}