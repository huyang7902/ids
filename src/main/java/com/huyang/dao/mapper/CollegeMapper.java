package com.huyang.dao.mapper;

import com.huyang.dao.po.College;
import com.huyang.dao.po.CollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollegeMapper {
    int countByExample(CollegeExample example);

    int deleteByExample(CollegeExample example);

    int deleteByPrimaryKey(String id);

    int insert(College record);

    int insertSelective(College record);

    List<College> selectByExample(CollegeExample example);

    College selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByExample(@Param("record") College record, @Param("example") CollegeExample example);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
}