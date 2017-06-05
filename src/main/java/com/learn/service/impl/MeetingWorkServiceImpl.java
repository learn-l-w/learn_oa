package com.learn.service.impl;

import com.learn.dao.MeetingWorkDao;
import com.learn.model.Department;
import com.learn.model.MeetingRoom;
import com.learn.model.MeetingWork;
import com.learn.service.DepartmentService;
import com.learn.service.MeetingRoomService;
import com.learn.service.MeetingWorkService;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@Service
public class MeetingWorkServiceImpl implements MeetingWorkService {

    @Autowired
    private MeetingWorkDao mwDao;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MeetingRoomService meetingRoomService;

    @Override
    public List<MeetingWork> selectMeetingWork(String meetingStatus, String meetingDepartment) {
        //根据会议状态查询相应的会议数据
       List<MeetingWork> meetingWorks = mwDao.selectMeetingWorkByStatus(meetingStatus);
        //根据部门名称去查询相应的部门名称（模糊查询）
        Department department = departmentService.selectDepartmentByName(meetingDepartment);
        for(MeetingWork meetingWork:meetingWorks){
            //根据会议室id查询会议室名称
           MeetingRoom meetingRoom = meetingRoomService.selectMeetingRoomByNameId(meetingWork.getMeetingRoomId());
            //设置会议室名称
            meetingWork.setMeetingRoomName(meetingRoom.getMeetingRoomName());
            //得到并赋值部门名称
            meetingWork.setMeetingDepartmentTitle(department.getTitle());
        }
            return meetingWorks;
    }

    @Override
    public void insertMeetingWork(MeetingWork meetingWork) {
        int startTime=meetingWork.getMeetingStartTime();
        int stopTime=meetingWork.getMeetingStopTime();
        setMeetingStatusBySet(startTime, stopTime,meetingWork);//调用设置会议状态的方法
        mwDao.insertMeetingWork(meetingWork);
        System.out.println(meetingWork.getMeetingStatus()+"========================================");

    }

    @Override
    public List<MeetingWork> selectAllMeetingWork() {
        List<MeetingWork> meetingWorkList = mwDao.selectAllMeetingWork();

        for(MeetingWork meetingWork:meetingWorkList){
            //获取部门id，去部门表查询,获取部门名称
            Department department = departmentService.selectOneDepartmentByID(meetingWork.getMeetingDepartmentId());
            meetingWork.setMeetingDepartmentTitle(department.getTitle());

            //获取会议名称id，去会议室表查询，获取会议室
            MeetingRoom meetingRoom =  meetingRoomService.selectMeetingRoomByNameId(meetingWork.getMeetingRoomId());
            meetingWork.setMeetingRoomName(meetingRoom.getMeetingRoomName());

        }

        return meetingWorkList;
    }

    //设置会议状态的方法
    private MeetingWork setMeetingStatusBySet(int startTime,int stopTime,MeetingWork meetingWork){

        int nowTime = (int) Calendar.getInstance().getTimeInMillis();//获取当前时间戳
        if(nowTime<startTime){
            meetingWork.setMeetingStatus("会议未开始");
        }else if(nowTime>startTime && nowTime<stopTime){
            meetingWork.setMeetingStatus("会议正在进行中...");
        }else{
            meetingWork.setMeetingStatus("会议已结束");
        }

        return meetingWork;
    }


}
