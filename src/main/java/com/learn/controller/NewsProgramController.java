package com.learn.controller;

import com.learn.model.NewsProgram;
import com.learn.service.NewsProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by Wangqingyu on 2017/5/29.
 */
@Controller
@Path("/news")
public class NewsProgramController extends BaseController {

    @Autowired
    private NewsProgramService newsProgramService;

    @GET
    @Path("/select")
    public List<NewsProgram> selectNewsProgram(){
        return newsProgramService.selectNewsProgram();
    }

    @GET
    @Path("/selectNews")
    public List<NewsProgram> selectNews(@QueryParam("newsName") String newsName,@QueryParam("programName")String programName){
        return newsProgramService.selectNewsByName(newsName, programName);

    }
}
