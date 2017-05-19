package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqingyu on 2017/5/18.
 */
public class UserDepartmentControllerTest extends BaseTest {

    @Test
    public void insertUserAndDepartmentTest(){

        Map<String,Object> params = new HashMap<>();
        params.put("userId",1);
        params.put("positionId",2);
        JsonNode res = httpPost("/userDepartm/insert",params);
        printObj(res);
    }
}
