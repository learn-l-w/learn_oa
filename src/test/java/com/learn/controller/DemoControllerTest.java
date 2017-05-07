package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/7
 * Time: 上午10:04
 */
public class DemoControllerTest extends BaseTest {

    @Test
    public void testInsert() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "title");
        params.put("desc", "desc");
        JsonNode res = httpPost("/demo/insert", params);
        printObj(res);
    }

    @Test
    public void testUpdate() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", "2");
        params.put("title", "title2");
        params.put("desc", "desc2");
        JsonNode res = httpPost("/demo/update", params);
        printObj(res);
    }

    @Test
    public void testDetail() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 3);
        JsonNode res = httpGet("/demo/detail", params);
        printObj(res);
    }

    @Test
    public void testDelete() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 3);
        JsonNode res = httpPost("/demo/delete", params);
        printObj(res);
    }

}
