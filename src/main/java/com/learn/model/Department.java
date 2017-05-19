package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by Administrator on 2017/5/17.
 */
public class Department extends BaseModel {

    private String title;
    private String remark;

    public Department() {
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
