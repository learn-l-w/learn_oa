package com.learn.service.impl;

import com.learn.dao.NewsProgramDao;
import com.learn.model.NewsProgram;
import com.learn.service.NewsProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wangqingyu on 2017/5/29.
 */
@Service
public class NewsProgramServiceImpl implements NewsProgramService {

    @Autowired
    private NewsProgramDao newsProgramDao;

    @Override
    public List<NewsProgram> selectNewsProgram() {
       List<NewsProgram> newsList = newsProgramDao.selectNews();
        for(NewsProgram news : newsList ){
            //根据新闻表的父ID去栏目表查询上级栏目名称
            NewsProgram newsProgram = newsProgramDao.selectProgramName(news.getFatherID());
            news.setProgramName(newsProgram.getProgramName());
            news.setProgramRemark(newsProgram.getProgramRemark());
            System.out.println(newsProgram);
        }
        return newsList;
    }

    @Override
    public List<NewsProgram> selectNewsByName(String newsName, String programName) {
        System.out.println("newsName="+newsName);
        System.out.println("programName="+programName);
        List<NewsProgram> newsPrograms = newsProgramDao.selectNewsByName(newsName,programName);
        System.out.println("newsPrograms="+newsPrograms+"==============================");
        return newsPrograms;
    }
}
