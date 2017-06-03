package com.learn.dao;

import com.learn.model.NewsProgram;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/29.
 */
@Mapper
public interface NewsProgramDao {
    List<NewsProgram> selectNews();
    NewsProgram selectProgramName(int fatherID);
    List<NewsProgram> selectNewsByName(@Param("newsName") String newsName,@Param("programName") String programName);
}
