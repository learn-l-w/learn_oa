package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/6
 * Time: 上午10:32
 */
public class Demo extends BaseModel {

    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
