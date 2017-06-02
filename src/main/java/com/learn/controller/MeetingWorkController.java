package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.MeetingWork;
import com.learn.service.MeetingWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
@Controller
@Path("/MeetingWork")
public class MeetingWorkController extends BaseController{

    @Autowired
    private MeetingWorkService mwService;

    @GET
    @Path("/selectMeetingWork")
    public List<MeetingWork> selectMeetingWork(@QueryParam("meetingStatus") String meetingStatus,@QueryParam("meetingDepartment") String meetingDepartment){
            return mwService.selectMeetingWork(meetingStatus,meetingDepartment);
    }

    @POST
    @Path("/insertMeetingWork")
    public Map<String,Object> insertMeetingWork(JsonNode jsonNode){
        MeetingWork meetingWork = getMeetingWork(jsonNode);
        mwService.insertMeetingWork(meetingWork);
        return returnMap(KEY_RESULT, "1");
    }

    @GET
    @Path("/selectAllMeetingWork")
    public List<MeetingWork> selectAllMeetingWork(){
        System.out.println("进入Controller层");
        return mwService.selectAllMeetingWork();
    }

    private MeetingWork getMeetingWork(JsonNode jsonNode){
        MeetingWork meetingWork = new MeetingWork();
        meetingWork.setMeetingName(getJsonText(jsonNode, "meetingName",false));
        meetingWork.setMeetingRoomId(getJsonInt(jsonNode, "meetingRoomId", false));
        meetingWork.setMeetingDepartmentId(getJsonInt(jsonNode, "meetingDepartmentId", false));
        meetingWork.setMeetingDepartmentTitle(getJsonText(jsonNode,"meetingDepartmentTitle",false));
        meetingWork.setMeetingStartTime(getJsonInt(jsonNode, "meetingStartTime",false));
        meetingWork.setMeetingStopTime(getJsonInt(jsonNode, "meetingStopTime",false));
        meetingWork.setMeetingStatus(getJsonText(jsonNode,"meetingStatus",false));
        return meetingWork;
    }
}
