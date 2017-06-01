package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by Administrator on 2017/5/29.
 */
public class NewsProgram extends BaseModel {

    private String newsName;
    private String programRemark;
    private int fatherID;
    private String programName;

    public NewsProgram() {
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getProgramRemark() {
        return programRemark;
    }

    public void setProgramRemark(String programRemark) {
        this.programRemark = programRemark;
    }

    public int getFatherID() {
        return fatherID;
    }

    public void setFatherID(int fatherID) {
        this.fatherID = fatherID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
