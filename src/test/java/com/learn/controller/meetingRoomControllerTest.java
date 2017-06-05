package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Wangqingyu on 2017/5/25.
 */
public class MeetingRoomControllerTest extends BaseTest{

    @Test
    public void insertTest(){

        Map<String,Object> params = new HashMap<>();
        params.put("meetingRoomName","Room6");
        params.put("meetingRemark","This is Room6");
        params.put("meetingRoomNumber",20);
        JsonNode res = httpPost("/meetingRoom/insertMeetingRoom",params);
        printObj(res);
    }

    @Test
    public void updateTest(){

        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        params.put("meetingRoomName","Room7");
        params.put("meetingRemark","it is Room7&it is Room8&it is Room9");
        params.put("meetingRoomNumber",25);
        JsonNode res = httpPost("/meetingRoom/updateMeetingRoom",params);
        printObj(res);
    }
}
