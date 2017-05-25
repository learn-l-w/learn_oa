package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/20.
 */
public class WorkDailyTest extends BaseTest {

    @Test
    public void insertWorkDailyTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("title","刘爽");
        params.put("remark","刘爽是超人");
        JsonNode res = httpPost("/workdaily/insertWorkDaily",params);
        printObj(res);
    }
}
