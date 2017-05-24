package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9.
 */
public class UserControllerTest extends BaseTest {

    @Test
    public void detailTest(){
        //dao层@param的用法
        Map<String, Object> params = new HashMap<>();
        params.put("id", 8);
        params.put("password", "7218729");
        params.put("newpassword", "334455");
        JsonNode res = httpPost("/user/update", params);
        printObj(res);
    }

    @Test
    public void dropTest(){
        Map<String, Object> params = new HashMap<>();
        params.put("ids","1,2,3");
        JsonNode res = httpPost("/user/drop", params);
        printObj(res);
    }

    @Test
    public void insertTest(){
        Map<String, Object> params = new HashMap<>();
        params.put("id",13);
        params.put("email","353004524@qq.com");
        params.put("phone","130145966901");
        params.put("username","ff");
        params.put("password","7218729");
        params.put("qq","353004524");
        params.put("sex",0);
        params.put("depId",1);
        params.put("address","henansq");
        params.put("birthday",19920728);
        params.put("postal_address","zhongguobeijing");
        params.put("remark","I am a superMan");
        params.put("persId",2);
        params.put("mgrId",2);
        params.put("job","技工");
        JsonNode res = httpPost("/user/insertUser", params);
        printObj(res);
    }

    @Test
    public void updateUserTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",11);
        params.put("persId",200);
        params.put("mgrId",200);
        params.put("username","lihua");
        params.put("job","Boss1");
        params.put("phone","333333");
        params.put("depId",8);
        JsonNode res = httpPost("/user/updateUser",params);
        printObj(res);
    }

    @Test
    public void arrayTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("ids",1&2&3);
        JsonNode res = httpPost("/user/array",params);
        printObj(res);
    }
}