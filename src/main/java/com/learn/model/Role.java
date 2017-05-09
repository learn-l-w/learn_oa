package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created with InterlliJ IDEA.
 * User: linfei
 * Date: 17/5/8
 * Time: pm 5:58
 */
public class Role extends BaseModel {

    private String remark;
    private String title;

    public Role() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
