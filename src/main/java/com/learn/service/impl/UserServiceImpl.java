package com.learn.service.impl;

import com.learn.dao.UserDao;
import com.learn.exception.LearnException;
import com.learn.model.User;
import com.learn.model.base.PageList;
import com.learn.service.UserService;
import com.learn.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        // TODO: 17/6/4 有密码一样的用户怎么办？
        // TODO: 17/6/4 命名要规范
       String passwor = MD5Utils.getMD5String(password);
        User s = uDao.selectByPassword(passwor);

        if (s==null){
            throw new LearnException("账号或密码错误");
        }else if(s.getDel()>0){
            throw new LearnException("用户不存在");
        }

      return s;
    }

    @Override
    public User lookById(Integer id) {
        User u = uDao.selectById(id);
        if(u==null){
            throw new LearnException("此用户id不存在");
        }
        return u;
    }

    @Override
    public void detail(Integer id,String password,String newpassword) {
        // TODO: 17/6/4 函数名叫detail？

        // TODO: 17/6/4 和上面问题一样  密码重复怎么办？假设用户表里有一百个用户，是不是蒙对一个就可以改密码了？
        String passwor = MD5Utils.getMD5String(password);
        if(uDao.selectByPassword(passwor)==null){
            throw new LearnException("账号或密码错误");
        }else{
            uDao.update(id, MD5Utils.getMD5String(newpassword));
        }
    }

    public List<User> selectAllUser(){
        return uDao.selectAllUser();
    }


    @Override
    public void delete(Integer id) {

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
        pageList.setTotal(uDao.getTotal());
        return pageList;
    }

    @Override
    public void insertUser(User user) {
        user.setPassword(MD5Utils.getMD5String(user.getPassword()));
        uDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        uDao.updateUser(user);
    }
}
