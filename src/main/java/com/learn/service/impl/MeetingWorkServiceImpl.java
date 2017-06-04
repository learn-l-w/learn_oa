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
        System.out.println(meetingWorks+"=============================================");
        //根据部门名称去查询相应的部门名称（模糊查询）
        // TODO: 17/6/4 这个不是模糊查询，是否改成根据id查询更好，前端直接传id
        Department department = departmentService.selectDepartmentByName(meetingDepartment);
        System.out.println(department+"=============================================");
        for(MeetingWork meetingWork:meetingWorks){
            //根据会议室id查询会议室名称
            System.out.println(meetingWork.getMeetingRoomId()+"=meetingWork.getMeetingRoomId()-=-=-=-=-=-=-=-=-=-=-=-=-");
            // TODO: 17/6/4 for循环里尽量不要写有dao操作的代码，尽量写到外边，想想怎么写
           MeetingRoom meetingRoom = meetingRoomService.selectMeetingRoomByNameId(meetingWork.getMeetingRoomId());
            //设置会议室名称
            meetingWork.setMeetingName(meetingRoom.getMeetingRoomName());
            //得到并赋值部门名称
            meetingWork.setMeetingDepartmentTitle(department.getTitle());
        }
            return meetingWorks;
    }

    @Override
    public void insertMeetingWork(MeetingWork meetingWork) {
        int startTime=meetingWork.getMeetingStartTime();
        int stopTime=meetingWork.getMeetingStopTime();
        setMeetingStatus(startTime,stopTime);
        mwDao.insertMeetingWork(meetingWork);
    }

    @Override
    public List<MeetingWork> selectAllMeetingWork() {
        List<MeetingWork> meetingWorkList = mwDao.selectAllMeetingWork();
        // TODO: 17/6/4 还是for里不要写dao
        // TODO: 17/6/4 如果meeting work有一万条数据，那此业务是不是会去mysql里查20001次？事实上三次就够了
        // TODO: 17/6/4 咱们数据库服务器在美国，每次查询大概300毫秒，两万次你算算多少，光是一个查询就要数个小时
        // TODO: 17/6/4 其实这就算是你说的大数据，数据量大了就要考虑的更多
        for(MeetingWork meetingWork:meetingWorkList){
            //获取部门id，去部门表查询,获取部门名称
            Department department = departmentService.selectOneDepartmentByID(meetingWork.getMeetingDepartmentId());
            meetingWork.setMeetingDepartmentTitle(department.getTitle());

            //获取会议名称id，去会议室表查询，获取会议室
            MeetingRoom meetingRoom =  meetingRoomService.selectMeetingRoomByNameId(meetingWork.getMeetingRoomId());
            meetingWork.setMeetingRoomName(meetingRoom.getMeetingRoomName());

            //设置会议状态
            int startTime = meetingWork.getMeetingStartTime();
            int stopTime = meetingWork.getMeetingStopTime();
            System.out.println("进入设置会议状态");
            setMeetingStatus(startTime, stopTime);
            System.out.println(meetingWork.getMeetingStatus()+"----------------------------");
        }

        return meetingWorkList;
    }

    //设置会议状态的方法
    // TODO: 17/6/4 这个函数不要叫这个名字，有歧义
    private MeetingWork setMeetingStatus(int startTime,int stopTime){
        System.out.println("进入方法");
        MeetingWork meetingWork = new MeetingWork();
        int nowTime = (int) Calendar.getInstance().getTimeInMillis();//获取当前时间戳
        System.out.println(nowTime+"获取当前时间戳");
        if(nowTime<startTime){
            System.out.println((nowTime<startTime)+"作比较======");
            meetingWork.setMeetingStatus("会议未开始");
        }else if(nowTime>startTime && nowTime<stopTime){
            meetingWork.setMeetingStatus("会议正在进行中...");
        }else{
            meetingWork.setMeetingStatus("会议已结束");
        }

        return meetingWork;
    }


}
