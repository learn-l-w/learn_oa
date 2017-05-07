package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.Demo;
import com.learn.service.DemoService;
import com.learn.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;

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

    @POST
    @Path("/update")
    public Map<String, Object> update(JsonNode jsonNode) {
        Demo demo = getDemoByJson(jsonNode);
        demoService.update(demo);
        return returnMap(KEY_RESULT, "1");// TODO: 17/5/6 need return id
    }

    @POST
    @Path("/insert")
    public Map<String, Object> insert(JsonNode jsonNode) {
        Demo demo = getDemoByJson(jsonNode);
        demoService.insert(demo);
        return returnMap(KEY_RESULT, "1");// TODO: 17/5/6 need return id
    }

    @POST
    @Path("/delete")
    public Map<String, Object> delete(JsonNode jsonNode) {
        int id = getJsonInt(jsonNode, "id", true);
        demoService.delete(id);
        return returnMap(KEY_RESULT, "1");// TODO: 17/5/6 need return id
    }

    private Demo getDemoByJson(JsonNode jsonNode) {
        Demo demo = new Demo();
        demo.setId(getJsonInt(jsonNode, "id", false));
        demo.setTitle(getJsonText(jsonNode, "title", false));
        demo.setDesc(getJsonText(jsonNode, "desc", false));
        return demo;
    }
}
