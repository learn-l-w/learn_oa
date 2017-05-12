package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
public class RoleUserControllerTest extends BaseTest {

    @Test
    public void insertRoleAndUserTest(){
        Map<String, Object> params = new HashMap<>();
        params.put("id",6);
        params.put("roleId",1);
        params.put("userId",1);
        JsonNode res = httpPost("/roleUser/insertRoleAndUser",params);
        printObj(res);
    }

    @Test
    public void deleteRoleAndUserTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        JsonNode res = httpPost("/roleUser/deletRoleIdAndUserId",params);
        printObj(res);
    }
}
