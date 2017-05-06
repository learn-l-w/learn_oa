package com.learn.model.base;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/6
 * Time: 上午10:32
 */
public class BaseModel {

    private int id;
    private int updateTime;
    private int time;
    private int del;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
