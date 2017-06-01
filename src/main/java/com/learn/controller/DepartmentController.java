package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.Department;
import com.learn.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/17.
 */
@Controller
@Path("/departm")
public class DepartmentController extends BaseController{

    @Autowired
    private DepartmentService dmService;

    @POST
    @Path("/insert")
    public Map<String,Object> insertDepm(JsonNode jsonNode){
        Department department = getDepartment(jsonNode);
        dmService.insertDemp(department);
        return returnMap(KEY_RESULT, "1");
    }

    @GET
    @Path("/select")
    public Department selectDepartmentByName(@QueryParam("meetingDepartment") String meetingDepartment){
       return dmService.selectDepartmentByName(meetingDepartment);
    }

    @POST
    @Path("/update")
    public Map<String,Object> updateDepartment(JsonNode jsonNode){
        Integer id = getJsonInt(jsonNode,"id",false);
        String title = getJsonText(jsonNode, "title", false);
        String remark = getJsonText(jsonNode,"remark",false);
        dmService.updateDepartment(id,title,remark);
        return returnMap(KEY_RESULT, "1");
    }

    @POST
    @Path("/delete")
    public Map<String,Object> deleteDepartment(JsonNode jsonNode){
        Integer id = getJsonInt(jsonNode, "id", false);
        dmService.deleteDepartment(id);
        return returnMap(KEY_RESULT, "1");
    }


    private Department getDepartment(JsonNode jsonNode){
        Department department = new Department();
        department.setTitle(getJsonText(jsonNode, "title", false));
        department.setRemark(getJsonText(jsonNode,"remark",false));
        return department;
    }
}
