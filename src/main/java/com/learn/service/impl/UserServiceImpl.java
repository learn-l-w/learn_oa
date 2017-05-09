package com.learn.service.impl;

import com.learn.dao.UserDao;
import com.learn.exception.LearnException;
import com.learn.model.User;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: wangqingyu
 * Date: 17/5/7
 * Time: 上午14:22
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao uDao;

    @Override
    public User look(String password) {
        User s = uDao.selectByPassword(password);
        if (s==null){
            throw new LearnException("账号或密码错误");
        }
      return s;
    }

    @Override
    public User lookById(int id) {
        User u = uDao.selectById(id);
        if(u==null){
            throw new LearnException("此用户id不存在");
        }
        return u;
    }

    @Override
    public void detail(User u) {
        User us = uDao.selectByPassword(u.getPassword());
        if(us==null){
            throw new LearnException("账号或密码错误");
        }else{
            uDao.update(u);
        }
    }
}
