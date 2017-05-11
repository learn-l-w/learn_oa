package com.learn.service.impl;

import com.learn.dao.RoleDao;
import com.learn.exception.LearnException;
import com.learn.model.Role;
import com.learn.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with InterlliJ IDEA
 * User: linfei
 * Date: 2017/5/9
 * Time: pm 15:17
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role selectById(int id) {
        return roleDao.selectById(id);
    }

    @Override
    public void insert(Role role) {
        roleDao.insert(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(int id) {
        if( roleDao.selectById(id) == null){
            throw new LearnException("用户不存在de");
        }else{
            roleDao.delete(id);
        }

    }
}
