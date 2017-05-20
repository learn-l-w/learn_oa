package com.learn.dao;

import com.learn.model.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/17.
 */
@Mapper
public interface DepartmentDao {
    void insertDemp(Department department);
    Department selectDepartmentByName(String name);
    void updateDepartment(@Param("id")Integer id,@Param("title")String title,@Param("remark")String remark);
    void deleteDepartment(Integer id);
}
