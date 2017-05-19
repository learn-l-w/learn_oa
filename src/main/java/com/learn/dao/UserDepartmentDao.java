package com.learn.dao;

import com.learn.model.UserDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/18.
 */
@Mapper
public interface UserDepartmentDao {
    UserDepartment selectUserDepartment(@Param("userId")int userId,@Param("positionId")int positionId);
    void insertUserAndDepartment(UserDepartment userDepartment);
}
