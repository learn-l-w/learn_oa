package com.learn.service.impl;

import com.learn.dao.MeetingRoomDao;
import com.learn.model.MeetingRoom;
import com.learn.model.base.PageList;
import com.learn.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Wangqingyu on 2017/5/25.
 */
@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomDao mrDao;

    @Override
    public void insertMeetingRoom(MeetingRoom meetingRoom) {
        mrDao.insertMeetingRoom(meetingRoom);
    }

    @Override
    public PageList<MeetingRoom> selectMeetingRoom(int offset,int length,String meetingRoomName,int meetingRoomNumber) {
        PageList<MeetingRoom> pageList = new PageList<>();
        pageList.setList(mrDao.selectMeetingRoom(offset, length,meetingRoomName,meetingRoomNumber));
        pageList.setTotal(mrDao.getTotal(offset, length,meetingRoomName,meetingRoomNumber));
        return pageList;
    }

    @Override
    public void updateMeetingRoom(MeetingRoom meetingRoom) {
        mrDao.updateMeetingRoom(meetingRoom);
    }

    @Override
    public MeetingRoom selectMeetingRoomByNameId(Integer meetingRoomId) {
        return mrDao.selectMeetingRoomByNameId(meetingRoomId);
    }
}
