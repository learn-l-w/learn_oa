package com.learn.service.impl;

import com.learn.dao.RoleUserDao;
import com.learn.exception.LearnException;
import com.learn.model.Role;
import com.learn.model.RoleUser;
import com.learn.model.User;
import com.learn.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangqingyu on 2017/5/11.
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserDao ruDao;

    @Override
    public void insertRoleAndUser(RoleUser roleUser) {
       // for(String roleId : roleIds){
       // }
        if(ruDao.getSelectUserIdAndRoleId(roleUser.getUserId(),roleUser.getRoleId()) == null ){
            ruDao.insertRoleIdAndUserId(roleUser);
        }else{
            throw new LearnException("此角色用户不能重复关联");
        }
    }

    @Override
    public List<Role> getRoleByUserId(Integer userId) {
        return ruDao.getRoleByUserId(userId);
    }

    @Override
    public List<User> getUserByRoleId(Integer roleId) {
        return ruDao.getUserByRoleId(roleId);
    }

    @Override
    public void deleteRoleAndUser(Integer id) {
       /* int ruCount = ruDao.getSelectUserIdAndRoleId(roleUser);
        if(ruCount > 0 ){

        }*/
        ruDao.deleteRoleIdAndUserId(id);
    }
}
