package com.learn.dao;

import com.learn.model.Role;
import com.learn.model.RoleUser;
import com.learn.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangqingyu on 2017/5/11.
 */
@Mapper
public interface RoleUserDao {
    RoleUser getSelectUserIdAndRoleId(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
    void insertRoleIdAndUserId(RoleUser roleUser);
    List<Role> getRoleByUserId(Integer userId);
    List<User> getUserByRoleId(Integer roleId);
    void deleteRoleIdAndUserId(Integer id);
}
