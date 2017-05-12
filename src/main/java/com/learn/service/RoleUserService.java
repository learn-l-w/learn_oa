package com.learn.service;

import com.learn.model.Role;
import com.learn.model.RoleUser;
import com.learn.model.User;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface RoleUserService {
    void insertRoleAndUser(RoleUser roleUser);
    List<Role> getRoleByUserId(Integer userId);
    List<User> getUserByRoleId(Integer roleId);
    void deleteRoleAndUser(Integer id);
}
