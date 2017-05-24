package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.WorkDaily;
import com.learn.service.WorkDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

/**
 * Created by WangQingyu on 2017/5/20.
 */
@Controller
@Path("/workdaily")
public class WorkDailyController extends BaseController{

        @Autowired
        private WorkDailyService wdService;

    @POST
    @Path("/insertWorkDaily")
    public Map<String,Object> insertWorkDaily(JsonNode jsonNode){
       WorkDaily workDaily = getWorkDailyParams(jsonNode);
        wdService.insertWorkDaily(workDaily);
        return returnMap(KEY_RESULT, "1");
    }

    @GET
    @Path("/selectAllWork")
    public List<WorkDaily> selectAllWorkDaily(){
       return wdService.selectAllWorkDaily();
    }

    private WorkDaily getWorkDailyParams(JsonNode jsonNode){
        WorkDaily workDaily = new WorkDaily();
        workDaily.setTitle(getJsonText(jsonNode, "title",false));
        workDaily.setRemark(getJsonText(jsonNode,"remark",false));
        return workDaily;
    }
}
