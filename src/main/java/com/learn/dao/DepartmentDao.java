package com.learn.dao;

import com.learn.model.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2017/5/17.
 */
@Mapper
public interface DepartmentDao {
    void insertDemp(Department department);
    Department selectDepartmentByName(String name);
}
