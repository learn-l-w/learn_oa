package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.Role;
import com.learn.model.RoleUser;
import com.learn.model.User;
import com.learn.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
@Controller
@Path("/roleUser")
public class RoleUserController extends BaseController{

    @Autowired
    private RoleUserService rsService;

    @POST
    @Path("/insertRoleAndUser")
    public Map<String, Object> insertRoleAndUser(JsonNode jsonNode){
        RoleUser roleUser = getRoleUserByJson(jsonNode);
        rsService.insertRoleAndUser(roleUser);
        return returnMap(KEY_RESULT, "1");
    }

    @GET
    @Path("/selectRoleByUserId")
    public List<Role> selectRoleByUserId(@QueryParam("userId") Integer userId){
        return rsService.getRoleByUserId(userId);
    }

    @GET
    @Path("/selectUserById")
    public List<User> selectUserById(@QueryParam("roleId") Integer roleId){
        return rsService.getUserByRoleId(roleId);
    }

    @POST
    @Path("/deletRoleIdAndUserId")
    public Map<String,Object> deletRoleIdAndUserId(JsonNode jsonNode){
        Integer id = getJsonInt(jsonNode,"id",false);
        rsService.deleteRoleAndUser(id);
        return returnMap(KEY_RESULT, "1");
    }

    public RoleUser getRoleUserByJson(JsonNode jsonNode){
        RoleUser roleUser = new RoleUser();
        roleUser.setId(getJsonInt(jsonNode,"id",false));
        roleUser.setRoleId(getJsonInt(jsonNode, "roleId", false));
        roleUser.setUserId(getJsonInt(jsonNode,"userId",false));
        return roleUser;
    }
}
