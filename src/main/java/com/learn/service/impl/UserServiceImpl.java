package com.learn.service.impl;

import com.learn.dao.UserDao;
import com.learn.exception.LearnException;
import com.learn.model.User;
import com.learn.model.base.PageList;
import com.learn.service.UserService;
import com.learn.util.MD5Utils;
import com.learn.util.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;


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
    private HashMap<String,User> catchMap;


    @Override
    public User look(String password) {
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
        String ids = String.valueOf(id);
        User user = null;
        if(getCacheMap().get(ids)==null){
            user = uDao.selectById(id);
            putCache(ids, user);
        }else{
            user = getCache(ids);
        }

        return user;
    }

    @Override
    public void detail(Integer id,String password,String newpassword) {
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
        pageList.setList(uDao.selectUser(offset, length));
        pageList.setTotal(uDao.getTotal());
        return pageList;
    }
    //注册账户
    @Override
    public void insertUser(User user) throws MessagingException {
        String uuid = String.valueOf(UUID.randomUUID());//随机生成账户激活码
        user.setActiveCode(uuid);//设置账户激活码
        String email = MD5Utils.getMD5String(user.getEmail());//加密邮件
        user.setPassword(MD5Utils.getMD5String(user.getPassword()));//密码加密
        if(uDao.selectUserByEmail(user.getEmail())!=null){
            throw new LearnException("邮箱"+user.getEmail()+"已经被注册");
        }else{
            uDao.insertUser(user);
        }
        User emailStatus = uDao.selectUserByEmail(user.getEmail());
        if (emailStatus.getStatus()==0){//邮箱没有被激活
            //拼接邮件内容
            StringBuffer sb = new StringBuffer("尊敬的"+user.getUsername()+"先生您好，我爱您</br>");
            sb.append("<a href=\"http://localhost:8080/user/register?email=");
            sb.append(email);
            sb.append("&activeCode=");
            sb.append(user.getActiveCode());
            sb.append("\">http://localhost:8080/user/register?email=");
            sb.append(email);
            sb.append("&activeCode=");
            sb.append(user.getActiveCode());
            sb.append("</a>");
            //发送邮件
            SendEmailUtil.send(user.getEmail(), sb.toString());
        }
    }

    //根据邮箱修改账号激活状态
    @Override
    public void updateEmailStatus(String activeCode) {
        User user = uDao.selectTime(activeCode);//去用户表里获取注册时间
        int nowTime = (int)Calendar.getInstance().getTimeInMillis();//获取当前时间戳
        if((nowTime-user.getTime())>=48){//判断注册时间是否大于48小时
            throw new LearnException("此链接已失效");
        }else {
            uDao.updateEmailStatus(activeCode);
        }
    }

    @Override
    public void updateUser(User user) {
        String key = String.valueOf(user.getId());
        removeCache(key);
        uDao.updateUser(user);
    }
//----------缓存的方法--------------------------------------------------------------
    public User getCache(String key) {
        User user = getCacheMap().get(key);
        return user;
    }

    public void putCache(String key, User user) {
        getCacheMap().put(key, user);
    }

    public void removeCache(String key) {
        getCacheMap().remove(key);
    }

    public Map<String, User> getCacheMap() {
        if (catchMap == null){
            catchMap = new HashMap<>();
        }
        return catchMap;
    }
}

