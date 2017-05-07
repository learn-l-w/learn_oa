package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/6
 * Time: 上午10:32
 */
public class Demo extends BaseModel {

    // TODO: learn_1.0 理论上说所有实体都应该有一些共同里的属性，所以提出来个父类，继承就行

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
