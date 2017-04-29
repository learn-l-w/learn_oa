package com.learn.controller;

import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:46
 */
@Path("/")
@Controller
public class RootController extends BaseController {

    @GET
    public Object root() {
        return returnMap(KEY_RESULT, "running");
    }

}
