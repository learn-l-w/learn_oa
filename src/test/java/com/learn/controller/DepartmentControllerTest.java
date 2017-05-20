package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/17.
 */
public class DepartmentControllerTest extends BaseTest {

    @Test
    public void insertDempTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("title","yewubu1");
        params.put("remark","ci bumen shi ge ceshi bumen");
        JsonNode res = httpPost("/departm/insert",params);
        printObj(res);
    }

    @Test
    public void updateDepartmentTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",8);
        params.put("title","yuwubu2");
        params.put("remark","ci bumen yi xiugai");
        JsonNode res = httpPost("/departm/update",params);
        printObj(res);
    }

    @Test
    public void deleteDepartment(){
        Map<String,Object> params = new HashMap<>();
        params.put("id",6);
        JsonNode res = httpPost("/departm/delete",params);
        printObj(res);
    }
}
