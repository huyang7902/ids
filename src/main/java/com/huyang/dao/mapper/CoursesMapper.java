package com.huyang.dao.mapper;

import com.huyang.dao.po.Courses;
import com.huyang.dao.po.CoursesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoursesMapper {
    int countByExample(CoursesExample example);

    int deleteByExample(CoursesExample example);

    int insert(Courses record);

    int insertSelective(Courses record);

    List<Courses> selectByExample(CoursesExample example);

    int updateByExampleSelective(@Param("record") Courses record, @Param("example") CoursesExample example);

    int updateByExample(@Param("record") Courses record, @Param("example") CoursesExample example);
}