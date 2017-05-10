package com.learn.dao;

import com.learn.model.User;
import com.learn.model.base.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<User> selectUser(@Param("offset") int offset,@Param("length") int length);
    void insertUser(User user);
}
