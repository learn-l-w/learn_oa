package com.learn.dao;

import com.learn.model.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangqingyu on 2017/5/17.
 */
@Mapper
public interface DepartmentDao {
    void insertDemp(Department department);
    Department selectDepartmentByName(String meetingDepartment);
    void updateDepartment(@Param("id")Integer id,@Param("title")String title,@Param("remark")String remark);
    void deleteDepartment(Integer id);
    Department selectOneDepartmentByID(Integer depart_id);
}
