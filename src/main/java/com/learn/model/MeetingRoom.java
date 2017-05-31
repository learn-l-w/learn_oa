package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by Wangqingyu on 2017/5/25.
 */
public class MeetingRoom extends BaseModel{
    private String meetingRoomName;
    private String meetingRemark;
    private int meetingRoomNumber;


    public MeetingRoom() {
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public String getMeetingRemark() {
        return meetingRemark;
    }

    public void setMeetingRemark(String meetingRemark) {
        this.meetingRemark = meetingRemark;
    }

    public int getMeetingRoomNumber() {
        return meetingRoomNumber;
    }

    public void setMeetingRoomNumber(int meetingRoomNumber) {
        this.meetingRoomNumber = meetingRoomNumber;
    }
}
