package com.learn.service;


import com.learn.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: wangqingyu
 * Date: 17/5/7
 * Time: 上午14:18
 */
public interface UserService {
    User look(String password);
    User lookById(int id);
    void detail(User u);
}
