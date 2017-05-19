package com.learn.service.impl;

import com.learn.dao.DepartmentDao;
import com.learn.model.Department;
import com.learn.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao dmDao;

    @Override
    public void insertDemp(Department department) {
        dmDao.insertDemp(department);
    }

    @Override
    public Department selectDepartmentByName(String name) {
        return dmDao.selectDepartmentByName(name);

    }
}
