package com.learn.dao;

import com.learn.model.Department;
import com.learn.model.MeetingWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@Mapper
public interface MeetingWorkDao {
    List<MeetingWork> selectMeetingWorkByStatus(String meetingStatus);
    void insertMeetingWork(MeetingWork meetingWork);
    List<MeetingWork> selectAllMeetingWork();

}
