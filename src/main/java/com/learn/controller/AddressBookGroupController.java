package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.AddressBookGroup;
import com.learn.service.AddressBookGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by linfei on 2017/6/5.
 */
@Controller
@Path("/addressBookGroup")
public class AddressBookGroupController extends BaseController{

    @Autowired
    private AddressBookGroupService addressBookGroupService;


    @GET
    @Path("/selectAll")
    public List<AddressBookGroup> selectAll(){
        return addressBookGroupService.selectAll();
    }

    @GET
    @Path("/select")
    public AddressBookGroup select(@QueryParam("title") String title){
        return addressBookGroupService.selectByTitle(title);
    }


    @POST
    @Path("/insert")
    public Map<String, Object> insert(JsonNode jsonNode){
        AddressBookGroup addressBookGroup = getAddressBookGroupByJson(jsonNode);
        addressBookGroupService.insert(addressBookGroup);
        return returnMap(KEY_RESULT, "1");
    }

    @POST
    @Path("/update")
    public Map<String, Object> update(JsonNode jsonNode){
        AddressBookGroup addressBookGroup = getAddressBookGroupByJson(jsonNode);
        addressBookGroupService.update(addressBookGroup);
        return returnMap(KEY_RESULT," 1");
    }

    @POST
    @Path("/delete")
    public Map<String, Object> delete(JsonNode jsonNode){

        String sid = getJsonText(jsonNode, "id", false);
        String[] ArrayIda = sid.split("\\,");
        for(String ida:ArrayIda){
           Integer id = Integer.valueOf(ida);
            addressBookGroupService.delete(id);
        }
        return returnMap(KEY_RESULT, "1");
    }

    private AddressBookGroup getAddressBookGroupByJson(JsonNode jsonNode){
        AddressBookGroup addressBookGroup = new AddressBookGroup();
        addressBookGroup.setId(getJsonInt(jsonNode, "id", false));
        addressBookGroup.setRemark(getJsonText(jsonNode, "remark", false));
        addressBookGroup.setTitle(getJsonText(jsonNode, "title", false));
        addressBookGroup.setTime(getJsonInt(jsonNode, "time", false));
        addressBookGroup.setUpdateTime(getJsonInt(jsonNode, "updateTime", false));
        return addressBookGroup;
    }

}
