package com.learn.dao;

import com.learn.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * User: wangqingyu
 * Date: 17/5/7
 * Time: 上午15:26
 */
@Mapper
public interface UserDao {

    User selectByPassword(String password);
    User selectById(int id);
    void update(@Param("id") Integer id,@Param("newpassword") String newpassword);
    void deleteId(Integer id);
}
