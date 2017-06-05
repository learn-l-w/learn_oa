package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 小野花 on 2017/6/5.
 */
public class AddressBookGroupControllerTest extends BaseTest {

    @Test
    public void testInsert() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "组员");
        params.put("remark", "下属");
        JsonNode res = httpPost("/addressBookGroup/insert", params);
        printObj(res);
    }

    @Test
    public void testUpdate() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("title", "朋友");
        params.put("remark", "好哥们");
        JsonNode res = httpPost("/addressBookGroup/update", params);
        printObj(res);
    }

    @Test
    public void testDelete(){
        Map<String, Object> params = new HashMap<>();
        params.put("id", "1,2,3");
        JsonNode res = httpPost("/addressBookGroup/delete", params);
        printObj(res);
    }
}
