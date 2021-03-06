package com.learn.service;


import com.learn.model.User;
import com.learn.model.base.PageList;

import javax.mail.MessagingException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: wangqingyu
 * Date: 17/5/7
 * Time: 上午14:18
 */
public interface UserService {
    List<User> selectUserByAddressBook();
    User look(String password);
    User lookById(Integer id);
    List<User> selectAllUser();
    void detail(Integer id,String password,String newpassword);
    void delete(Integer id);
    PageList<User> getPage(int offset,int length);
    void insertUser (User user) throws MessagingException;
    void updateUser(User user);

    void updateEmailStatus(String activeCode);
}
