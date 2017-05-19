package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by Administrator on 2017/5/18.
 */
public class UserDepartment extends BaseModel {

    private int userId;
    private int positionId;

    public UserDepartment() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
