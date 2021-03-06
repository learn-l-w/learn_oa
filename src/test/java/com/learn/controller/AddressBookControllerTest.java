package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 小野花 on 2017/5/24.
 */
public class AddressBookControllerTest extends BaseTest {

    @Test
    public void testUpdate() {
        Map<String, Object> params = new HashMap<>();
        params.put("id",1);
        params.put("address","北京");
        params.put("qq","henansq");
        params.put("email","www");
        params.put("sex","0");
        params.put("phone","eee");
        JsonNode res = httpPost("/addressBook/update", params);
        printObj(res);
    }

    @Test
    public void testDelete() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        JsonNode res = httpPost("/addressBook/delete", params);
        printObj(res);
    }
}
