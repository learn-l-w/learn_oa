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
        params.put("id", 4);
        JsonNode res = httpPost("/user/drop", params);
        printObj(res);
    }

    @Test
    public void insertTest(){
        Map<String, Object> params = new HashMap<>();
        params.put("id",8);
        params.put("email","353004524@qq.com");
        params.put("phone","130145966901");
        params.put("username","Godawp");
        params.put("password","7218729");
        params.put("qq","353004524");
        JsonNode res = httpPost("/user/insertUser", params);
        printObj(res);
    }
}