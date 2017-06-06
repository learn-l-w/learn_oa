package com.learn.service;

import com.learn.model.NewsProgram;

import java.util.List;

/**
 * Created by Wangqingyu on 2017/5/29.
 */
public interface NewsProgramService {
    List<NewsProgram> selectNewsProgram();
    List<NewsProgram> selectNewsByName(String newsName,String programName);
}
