package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by wangqingyu on 2017/5/20.
 */
public class WorkDaily extends BaseModel{
    private String title;
    private String remark;

    public WorkDaily() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
