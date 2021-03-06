package com.learn.dao;

import com.learn.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with InterlliJ IDEA.
 * User: linfei
 * Date: 2017/5/9
 * Time: pm 15:00
 */

@Mapper
public interface RoleDao {

    Role selectById(int id);

    int insert(Role role);
    int update(Role role);
    int delete(int id);
    List<Role> queryPage(@Param("offset") int offset,@Param("length") int length);
}
