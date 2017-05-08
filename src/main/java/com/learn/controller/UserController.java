package com.learn.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.learn.model.User;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created with IntelliJ IDEA.
 * User: wangqingyu
 * Date: 17/5/7
 * Time: 上午12:31
 */
@Controller
@Path("/user")
public class UserController extends BaseController{

    // TODO: learn_1.8 注意controller类和函数的注解
    // TODO 一般来说，查询用get，对数据修改用post
    // TODO controller中尽量不要写逻辑，逻辑写到service中去

     @Autowired
     private UserService uService;

    @GET
    @Path("/login")
    public User look(@QueryParam("password") int password){
       return uService.look(password);
    }

    @GET
    @Path("/look")
    public User lookById(@QueryParam("id") int id){
        return uService.lookById(id);
    }
}
