package com.learn.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.dao.UserDao;
import com.learn.exception.LearnException;
import com.learn.model.User;
import com.learn.model.base.PageList;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.Map;

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

        if (s.getDel()>0){
            throw new LearnException("用户不存在");
        }

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
    public void detail(Integer id,String password,String newpassword) {
        if(uDao.selectByPassword(password)==null){
            throw new LearnException("账号或密码错误");
        }else{
            uDao.update(id,newpassword);
        }
    }


    @Override
    public void drop(Integer id) {

        if(uDao.selectById(id) == null){
            throw new LearnException("此账号不存在");
        }else{
            uDao.deleteId(id);
        }
    }

    @Override
    public PageList<User> getPage(int offset,int length) {
        PageList<User> pageList = new PageList<User>();
        pageList.setList( uDao.selectUser(offset,length));
        //pageList.setTotal(uDao.getTotal);
        return pageList;
    }

    @Override
    public void insertUser(User user) {
        System.out.println(user.toString()+"=============================================");
        uDao.insertUser(user);
    }
}
