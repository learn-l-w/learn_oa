package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: linfei
 * Date: 17/5/10
 */
public class RoleControllerTest extends BaseTest {


    @Test
    public void testInsert(){
        Map<String, Object> params = new HashMap<>();
        params.put("title","总经理");
        params.put("remark","王庆宇1");
        JsonNode res = httpPost("/role/insert", params);
        printObj(res);
    }

    @Test
    public void testSelect(){
        Map<String, Object> params = new HashMap<>();
        params.put("id",3);
        JsonNode res = httpGet("/role/select", params);
        printObj(res);
    }

    @Test
    public void testUpdate(){
        Map<String, Object> params = new HashMap<>();
        params.put("id",3);
        params.put("title","王庆宇1" );
        params.put("remark","王庆宇1");
        JsonNode res = httpPost("/role/update", params);
        printObj(res);
    }

    @Test
    public void testDelete(){
        Map<String, Object> params = new HashMap<>();
        params.put("id",3);
        JsonNode res = httpPost("/role/delete", params);
        printObj(res);
    }





}
