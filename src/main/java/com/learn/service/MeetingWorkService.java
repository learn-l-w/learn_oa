package com.learn.service;

import com.learn.model.MeetingWork;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface MeetingWorkService {
    List<MeetingWork> selectMeetingWork(String meetingStatus,String meetingDepartment);
    void insertMeetingWork(MeetingWork meetingWork);
    List<MeetingWork> selectAllMeetingWork();
}
