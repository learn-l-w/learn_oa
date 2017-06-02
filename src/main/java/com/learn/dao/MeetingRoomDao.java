package com.learn.dao;

import com.learn.model.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Wangqingyu on 2017/5/25.
 */
@Mapper
public interface MeetingRoomDao {
    void insertMeetingRoom(MeetingRoom meetingRoom);
    List<MeetingRoom> selectMeetingRoom(@Param("offset") int offset,@Param("length")int length,@Param("meetingRoomName")String meetingRoomName,@Param("meetingRoomNumber")int meetingRoomNumber);
    int getTotal(@Param("offset") int offset,@Param("length")int length,@Param("meetingRoomName")String meetingRoomName,@Param("meetingRoomNumber")int meetingRoomNumber);
    void updateMeetingRoom(MeetingRoom meetingRoom);
    MeetingRoom selectMeetingRoomByNameId(Integer meetingRoomId);

}
