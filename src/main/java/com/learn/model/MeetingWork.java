package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by Wangqingyu on 2017/5/26.
 */
public class MeetingWork extends BaseModel{

    private String meetingName;//会议名称
    private Integer meetingRoomId;//会议室id
    private String meetingRoomName;//会议室名称
    private Integer meetingDepartmentId;//部门id
    private String meetingDepartmentTitle;//部门名称
    private String meetingStatus;//会议状态
    private int meetingStartTime;
    private int meetingStopTime;


    public MeetingWork() {
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Integer getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Integer meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public Integer getMeetingDepartmentId() {
        return meetingDepartmentId;
    }

    public void setMeetingDepartmentId(Integer meetingDepartmentId) {
        this.meetingDepartmentId = meetingDepartmentId;
    }

    public String getMeetingDepartmentTitle() {
        return meetingDepartmentTitle;
    }

    public void setMeetingDepartmentTitle(String meetingDepartmentTitle) {
        this.meetingDepartmentTitle = meetingDepartmentTitle;
    }

    public String getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(String meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    public int getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(int meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public int getMeetingStopTime() {
        return meetingStopTime;
    }

    public void setMeetingStopTime(int meetingStopTime) {
        this.meetingStopTime = meetingStopTime;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }
}
