package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.MeetingRoom;
import com.learn.model.base.PageList;
import com.learn.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;

/**
 * Created by Wangqingyu on 2017/5/25.
 */
@Controller
@Path("/meetingRoom")
public class MeetingRoomController extends BaseController {

    @Autowired
    private MeetingRoomService mrService;

    @POST
    @Path("/insertMeetingRoom")
    public Map<String,Object> insertMeetingRoom(JsonNode jsonNode){
        MeetingRoom meetingRoom = getMeetingRoom(jsonNode);
        mrService.insertMeetingRoom(meetingRoom);
        return returnMap(KEY_RESULT, "1");
    }

    @GET
    @Path("/selectMeetingRoom")
    public PageList<MeetingRoom> selectMeetingRoom(@QueryParam("offset") int offset,@QueryParam("length") int length,@QueryParam("meetingRoomName") String meetingRoomName,@QueryParam("meetingRoomNumber") int meetingRoomNumber){
        // TODO: 17/6/4 要考虑参数少传或者不传的情况
      return mrService.selectMeetingRoom(offset, length, meetingRoomName, meetingRoomNumber);
    }

    @POST
    @Path("/updateMeetingRoom")
    public Map<String,Object> updateMeetingRoom(JsonNode jsonNode){

        MeetingRoom meetingRoom = getMeetingRoom(jsonNode);
        mrService.updateMeetingRoom(meetingRoom);
        return returnMap(KEY_RESULT, "1");
    }

    private MeetingRoom getMeetingRoom(JsonNode jsonNode){
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setId(getJsonInt(jsonNode,"id",false));
        meetingRoom.setMeetingRoomName(getJsonText(jsonNode,"meetingRoomName",false));
        meetingRoom.setMeetingRemark(getJsonText(jsonNode, "meetingRemark", false));
        meetingRoom.setMeetingRoomNumber(getJsonInt(jsonNode,"meetingRoomNumber",false));
        return meetingRoom;

    }
}
