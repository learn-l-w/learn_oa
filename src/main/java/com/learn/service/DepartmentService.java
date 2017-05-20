package com.learn.service;

import com.learn.model.Department;

/**
 * Created by Administrator on 2017/5/17.
 */
public interface DepartmentService {

    void insertDemp(Department department);
    Department selectDepartmentByName(String name);
    void updateDepartment(Integer id,String title,String remark);
    void deleteDepartment(Integer id);
}
