package com.learn.service.impl;

import com.learn.dao.UserDepartmentDao;
import com.learn.exception.LearnException;
import com.learn.model.UserDepartment;
import com.learn.service.UserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangqingyu on 2017/5/18.
 */
@Service
public class UserDepartmentServiceImpl implements UserDepartmentService {

    @Autowired
    private UserDepartmentDao udmDao;

    @Override
    public void insertUserAndDepartment(UserDepartment userDepartment) {
        if(udmDao.selectUserDepartment(userDepartment.getUserId(),userDepartment.getPositionId()) == null){
            udmDao.insertUserAndDepartment(userDepartment);
        }else{
            throw new LearnException("此用户和部门不能重复关联");
        }
    }
}
