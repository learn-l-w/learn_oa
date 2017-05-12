package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by Administrator on 2017/5/11.
 */
public class RoleUser extends BaseModel {
    private Integer userId;
    private Integer roleId;

    public RoleUser() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
