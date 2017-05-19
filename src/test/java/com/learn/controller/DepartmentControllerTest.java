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
}
