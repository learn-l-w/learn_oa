package com.learn.service;

import com.learn.model.MeetingRoom;
import com.learn.model.base.PageList;

import java.util.List;


/**
 * Created by Wangqingyu on 2017/5/25.
 */
public interface MeetingRoomService {
    void insertMeetingRoom(MeetingRoom meetingRoom);
    PageList<MeetingRoom> selectMeetingRoom(int offset,int length,String meetingRoomName,int meetingRoomNumber);
    void updateMeetingRoom(MeetingRoom meetingRoom);
    MeetingRoom selectMeetingRoomByNameId(Integer meetingRoomId);
}
