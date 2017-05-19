package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.User;
import com.learn.model.base.PageList;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;

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
    public User look(@QueryParam("password") String password){
       return uService.look(password);
    }

    @GET
    @Path("/look")
    public User lookById(@QueryParam("id") int id){
        return uService.lookById(id);
    }

    @POST
    @Path("/update")
  /*  public Map<String,Object> detail(JsonNode jsonnode){
        User user = getUserByJson(jsonnode);
        uService.detail(user);
        return returnMap(KEY_RESULT, "1");
    }*/
      public Map<String,Object> detail(JsonNode jsonnode){

        Integer id = getJsonInt(jsonnode, "id", false);
        String password = getJsonText(jsonnode, "password", false);
        String newpassword = getJsonText(jsonnode, "newpassword", false);

        uService.detail(id, password, newpassword);

        return returnMap(KEY_RESULT, "1");
    }

    @POST
    @Path("/drop")
    public Map<String,Object> drop(JsonNode jsonnode){
        Integer id = getJsonInt(jsonnode, "id", false);
        uService.drop(id);
        return returnMap(KEY_RESULT, "1");
    }

    @GET
    @Path("/selectUser")
    public PageList<User> selectUser(@QueryParam("offset") int offset,@QueryParam("length") int length){
        return uService.getPage(offset,length);
    }

   /* private User getUserByJson(JsonNode jsonnode) {
        User user = new User();
        user.setId(getJsonInt(jsonnode, "id", false));
        user.setPassword(getJsonText(jsonnode, "password", false));
        user.setDel(getJsonInt(jsonnode,"del",false));
        return user;
    }*/

    @POST
    @Path("/insertUser")
    public Map<String, Object> insertUser(JsonNode jsonnode){
        User user = getUserByJson(jsonnode);
        uService.insertUser(user);
        return returnMap(KEY_RESULT, "1");
    }

    private User getUserByJson(JsonNode jsonnode){
        User user = new User();
        user.setId(getJsonInt(jsonnode, "id", false));
        user.setEmail(getJsonText(jsonnode, "email", false));
        user.setPhone(getJsonText(jsonnode, "phone", false));
        user.setQq(getJsonText(jsonnode, "qq", false));
        user.setUsername(getJsonText(jsonnode, "username", false));
        user.setPassword(getJsonText(jsonnode, "password", false));
        user.setTime(getJsonInt(jsonnode, "time", false));
        user.setUpdateTime(getJsonInt(jsonnode, "updateTime", false));
        user.setAddress(getJsonText(jsonnode, "address", false));
        user.setBirthday(getJsonInt(jsonnode, "birthday", false));
        user.setDepId(getJsonInt(jsonnode, "depId", false));
        user.setPostal_address(getJsonText(jsonnode, "postal_address",false));
        user.setRemark(getJsonText(jsonnode,"remark",false));
        return user;
    }
}
