package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.Role;
import com.learn.model.base.PageList;
import com.learn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created with InterlliJ IDEA.
 * User: linfei
 * Date: 17/5/8
 * Time: pm 5:28
 */

@Controller
@Path("/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @GET
    @Path("/select")
    public Role select (@QueryParam("id") int id){
        return roleService.selectById(id);
    }

    @GET
    @Path("/queryPage")
    public PageList<Role> queryPage(@QueryParam("offset") int offset,@QueryParam("length") int length ){
        return roleService.getPage(offset,length);
    }

    @POST
    @Path("/insert")
    public Map<String,Object> insert(JsonNode jsonNode){
        Role role = getRoleByJson(jsonNode);
        roleService.insert(role);
        return returnMap(KEY_RESULT, "1");
    }

    @POST
    @Path("/update")
    public Map<String,Object> update(JsonNode jsonNode){
        Role role = getRoleByJson(jsonNode);
        roleService.update(role);
        return returnMap(KEY_RESULT, "1");
    }

    @POST
    @Path("/delete")
    public Map<String,Object> delete (JsonNode jsonNode){
        Integer id = getJsonInt(jsonNode, "id", false);
        roleService.delete(id);
        return returnMap(KEY_RESULT, "1");
    }

    private Role getRoleByJson(JsonNode jsonNode){
        Role role = new Role();
        role.setId(getJsonInt(jsonNode, "id", false));
        role.setRemark(getJsonText(jsonNode, "remark", false));
        role.setTime(getJsonInt(jsonNode, "time", false));
        role.setUpdateTime(getJsonInt(jsonNode, "updateTime", false));
        role.setTitle(getJsonText(jsonNode, "title", false));
        return role;
    }




}
