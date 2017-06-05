package com.learn.dao;

import com.learn.BaseTest;
import com.learn.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/1.
 */
public class UserDaoTest extends BaseTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void insertUserTest(){
        User user = new User();
        user.setId(1);
        user.setAddress("河南商丘");
        user.setUsername("哇哇外");
        userDao.insertUser(user);
    }
}
