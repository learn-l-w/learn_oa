package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.UserDepartment;
import com.learn.service.UserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
@Controller
@Path("/userDepartm")
public class UserDepartmentController extends BaseController{

    @Autowired
    private UserDepartmentService udmService;

    @POST
    @Path("/insert")
    public Map<String,Object> insertUserAndDepartment(JsonNode jsonNode){
        UserDepartment userDepartment = getUserDepartment(jsonNode);
        udmService.insertUserAndDepartment(userDepartment);
        return returnMap(KEY_RESULT, "1");
    }

    private UserDepartment getUserDepartment(JsonNode jsonNode){
        UserDepartment userDepartment = new UserDepartment();
            userDepartment.setUserId(getJsonInt(jsonNode, "userId", false));
            userDepartment.setPositionId(getJsonInt(jsonNode,"positionId",false));
        return userDepartment;
    }
}
