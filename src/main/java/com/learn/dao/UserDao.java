package com.learn.dao;

import com.learn.model.User;
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
public interface UserDao{
    List<User> selectUserByAddressBook();
    User selectByPassword(String passwor);
    User selectById(Integer id);
    List<User> selectAllUser();
    void update(@Param("id") Integer id,@Param("newpassword") String newpassword);
    void deleteId(Integer id);
    List<User> selectUser(@Param("offset") int offset,@Param("length") int length);
    int getTotal();
    void insertUser(User user);
    User selectUserByEmail(String email);
    void updateEmailStatus(String activeCode);
    void updateUser(User user);
    User selectTime(String activeCode);
}
