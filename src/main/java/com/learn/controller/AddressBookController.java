package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.AddressBook;
import com.learn.service.AddressBookService;
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
@Path("/addressBook")
public class AddressBookController extends BaseController{

    @Autowired
    private AddressBookService addressBookService;

    @GET
    @Path("/select")
    public List<AddressBook> select(@QueryParam("title") String title,@QueryParam("email") String email,@QueryParam("phone") String phone,@QueryParam("qq") String qq,@QueryParam("address") String address,@QueryParam("sex") int sex,@QueryParam("time") int time,@QueryParam("update_time") int update_time){
        // TODO: 17/6/4 要考虑，少传几个参数，或者不传的情况
        return addressBookService.selectFunction(title,email,phone,qq,address,sex,time,update_time);
    }

    @POST
    @Path("/update")
    public Map<String,Object> update(JsonNode jsonNode){
        AddressBook addressBook = getAddressBookByJson(jsonNode);
        addressBookService.update(addressBook);
        return returnMap(KEY_RESULT,"1");
    }

    @POST
    @Path("/delete")
    public Map<String,Object> delete(JsonNode jsonNode){
        Integer id = getJsonInt(jsonNode, "id", true);
        addressBookService.delete(id);
        return returnMap(KEY_RESULT, "1");
    }

    private AddressBook getAddressBookByJson(JsonNode jsonNode){
        AddressBook addressBook = new AddressBook();
        addressBook.setId(getJsonInt(jsonNode, "id", false));
        addressBook.setSex(getJsonInt(jsonNode, "sex", false));
        addressBook.setEmail(getJsonText(jsonNode, "email", false));
        addressBook.setPhone(getJsonText(jsonNode, "phone", false));
        addressBook.setQq(getJsonText(jsonNode, "qq", false));
        addressBook.setTime(getJsonInt(jsonNode, "time", false));
        addressBook.setUpdateTime(getJsonInt(jsonNode, "updateTime", false));
        addressBook.setAddress(getJsonText(jsonNode, "address", false));
        addressBook.setJob(getJsonText(jsonNode, "job", false));
        addressBook.setDepartment(getJsonText(jsonNode, "department", false));

        return addressBook;
    }
}
