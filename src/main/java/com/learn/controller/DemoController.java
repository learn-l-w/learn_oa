package com.learn.controller;

import com.learn.model.Demo;
import com.learn.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/6
 * Time: 上午10:31
 */
@Controller
@Path("/demo")
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoService;

    @GET
    @Path("/detail")
    public Demo detail(@QueryParam("id") int id) {
        return demoService.selectById(id);
    }
}
