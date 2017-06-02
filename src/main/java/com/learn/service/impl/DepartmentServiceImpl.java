package com.learn.service.impl;

import com.learn.dao.DepartmentDao;
import com.learn.model.Department;
import com.learn.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangqingyu on 2017/5/17.
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
    public Department selectDepartmentByName(String meetingDepartment) {
        return dmDao.selectDepartmentByName(meetingDepartment);

    }

    @Override
    public void updateDepartment(Integer id,String title,String remark) {
        dmDao.updateDepartment(id, title, remark);
    }

    @Override
    public void deleteDepartment(Integer id) {
        dmDao.deleteDepartment(id);
    }

    @Override
    public Department selectOneDepartmentByID(Integer depart_id) {
        return dmDao.selectOneDepartmentByID(depart_id);
    }


}
