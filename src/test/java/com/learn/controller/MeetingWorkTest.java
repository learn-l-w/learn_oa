package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public class MeetingWorkTest extends BaseTest {

    @Test
    public void insertTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("meetingName","会议3");
        params.put("meetingRoom","会议室3");
        params.put("department","业务2部");
        params.put("meetingStartTime",2017526);
        params.put("meetingStopTime",2017527);
        JsonNode res = httpPost("/MeetingWork/insertMeetingWork",params);
        printObj(res);
    }
}
