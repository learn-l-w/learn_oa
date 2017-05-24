package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.AdressBook;
import com.learn.service.AdressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by 小野花 on 2017/5/22.
 */
@Controller
@Path("/adressBook")
public class AdressBookController extends BaseController{

    @Autowired
    private AdressBookService adressBookService;

    @GET
    @Path("/select")
    public List<AdressBook> select(@QueryParam("title") String title,@QueryParam("email") String email,@QueryParam("phone") String phone,@QueryParam("qq") String qq,@QueryParam("address") String address,@QueryParam("sex") int sex,@QueryParam("time") int time,@QueryParam("update_time") int update_time){

        return adressBookService.selectFunction(title,email,phone,qq,address,sex,time,update_time);
    }

    @POST
    @Path("/update")
    public Map<String,Object> update(JsonNode jsonNode){
        AdressBook adressBook = getAdressBookByJson(jsonNode);
        adressBookService.update(adressBook);
        return returnMap(KEY_RESULT,"1");
    }

    @POST
    @Path("/delete")
    public Map<String,Object> delete(JsonNode jsonNode){
        Integer id = getJsonInt(jsonNode, "id", true);
        adressBookService.delete(id);
        return returnMap(KEY_RESULT, "1");
    }

    private AdressBook getAdressBookByJson(JsonNode jsonNode){
        AdressBook adressBook = new AdressBook();
        adressBook.setId(getJsonInt(jsonNode, "id", false));
        adressBook.setSex(getJsonInt(jsonNode, "sex", false));
        adressBook.setEmail(getJsonText(jsonNode, "email", false));
        adressBook.setPhone(getJsonText(jsonNode, "phone", false));
        adressBook.setQq(getJsonText(jsonNode, "qq", false));
        adressBook.setTime(getJsonInt(jsonNode, "time", false));
        adressBook.setUpdateTime(getJsonInt(jsonNode, "updateTime", false));
        adressBook.setAddress(getJsonText(jsonNode, "address", false));
        adressBook.setJob(getJsonText(jsonNode, "job", false));
        adressBook.setDepartment(getJsonText(jsonNode, "department", false));

        return adressBook;
    }
}
