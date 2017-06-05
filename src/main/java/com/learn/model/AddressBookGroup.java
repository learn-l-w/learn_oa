package com.learn.model;

import com.learn.model.base.BaseModel;

/**
 * Created by 小野花 on 2017/6/5.
 */
public class AddressBookGroup extends BaseModel {


    private String title;
    private String remark;
    private String username;
    private String email;
    private String phone;

    public AddressBookGroup() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
