package com.learn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learn.util.JsonUtil;
import com.learn.util.StringUtil;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lucien on 15/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public abstract class BaseTest {
    @Test
    public void testBase() {
        System.out.println("Test!");
    }

    private static final String HOST = "http://localhost:8089";

    private Client client;

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    public BaseTest() {
        client = ClientBuilder.newClient().register(JacksonFeature.class);
        client.property(ClientProperties.CONNECT_TIMEOUT, 5000000);
        client.property(ClientProperties.READ_TIMEOUT, 5000000);
    }

    protected JsonNode httpPostException(String path, String body, boolean exception) {
        Map<String, Object> x = checkPathAndParams(path, null);
        path = (String) x.get("path");
        Map<String, Object> params = (Map) x.get("params");
        WebTarget target = client.target(HOST).path(path);
        if (params != null) {
            for (String k : params.keySet()) {
                target = target.queryParam(k, params.get(k));
            }
        }
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.json(body));
        System.out.println(target.getUri().toString());
        JsonNode result = null;
        MediaType responseMediaType = response.getMediaType();
        if (responseMediaType != null && responseMediaType.toString().contains(MediaType.APPLICATION_JSON)) {
            result = response.readEntity(JsonNode.class);
            System.out.println(result);
        } else {
            System.out.println(response.readEntity(String.class));
        }
        if (exception) {
            Assert.assertTrue(response.getStatus() != 200);
        } else {
            Assert.assertEquals(200, response.getStatus());
            return result;
        }
        return result;
    }

    protected JsonNode httpPost(String path, Map<String, Object> obj) {
        try {
            return httpPost(path, JsonUtil.toJson(obj));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected JsonNode httpPost(String path, ObjectNode obj) {
        try {
            return httpPost(path, obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected JsonNode httpPost(String path, String jsonStr) throws IOException {
        checkJson(jsonStr);
        return httpPostException(path, jsonStr, false);
    }

    protected JsonNode httpGet(String path, Map<String, Object> params) {
        return httpGetException(path, params, false);
    }

    protected JsonNode httpGetException(String path, Map<String, Object> params, boolean exception) {
        Map<String, Object> x = checkPathAndParams(path, params);
        path = (String) x.get("path");
        params = (Map) x.get("params");

        WebTarget target = client.target(HOST).path(path);
        if (params != null) {
            for (String k : params.keySet()) {
                target = target.queryParam(k, params.get(k));
            }
        }
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        invocationBuilder.cookie("test", "test");
        Response response = invocationBuilder.get();
        System.out.println(target.getUri().toString());
        JsonNode result = null;
        MediaType responseMediaType = response.getMediaType();
        if (responseMediaType != null && responseMediaType.toString().contains(MediaType.APPLICATION_JSON)) {
            result = response.readEntity(JsonNode.class);
            printObj(result);
        } else {
            printObj(response.readEntity(String.class));
        }
        if (exception) {
            Assert.assertTrue(response.getStatus() != 200);
        } else {
            Assert.assertEquals(200, response.getStatus());
        }
        return result;
    }

    protected void printObj(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> checkPathAndParams(String path, Map<String, Object> params) {
        int queryLen = path.indexOf("?");
        if (queryLen > 0) {
            String path1 = new String(path);
            path = path.substring(0, queryLen);
            if (params == null) {
                params = new HashMap<String, Object>();
            }
            List<String> ps = StringUtil.explode(path1.substring(queryLen + 1), "&");
            for (String p : ps) {
                List<String> p1 = StringUtil.explode(p, "=");
                params.put(p1.get(0), p1.get(1));
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("path", path);
        result.put("params", params);
        return result;
    }

    protected void assertJsonEqualString(JsonNode json, String string) {
        printObj("       Result: " + json.toString());
        printObj("Expect-Result: " + string);
        Assert.assertTrue(json.toString().equals(string));
    }

    protected void checkJson(String key) throws IOException {
        JsonUtil.jsonFromString(key);
    }


}
