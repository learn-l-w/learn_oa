package com.learn.service.impl;

import com.learn.dao.RoleDao;
import com.learn.exception.LearnException;
import com.learn.model.Role;
import com.learn.model.base.PageList;
import com.learn.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    private static HashMap<String,Role> cacheMap;

    @Override
    // 查询
    public Role selectById(Integer id) {
        Role role = null;
        String key = String.valueOf(id);
        // 去缓存查，若为空则缓存没有数据
        if(getCache(key)==null){
            role = roleDao.selectById(id);
            putCache(key,role);
        }
        role = getCache(key);
        return role;
    }

    @Override
    // 增加
    public void insert(Role role) {
        roleDao.insert(role);
    }

    @Override
    // 更新
    public void update(Role role) {
        String key = String.valueOf(role.getId());
        removeCache(key);// 更新数据之前清空缓存
        roleDao.update(role);
    }

    @Override
    // 分页查询
    public PageList<Role> getPage(int offset, int length){
        PageList<Role> pageList = new PageList<>();
        pageList.setList(roleDao.queryPage(offset, length));
        // TODO: 17/6/4 total去哪了
        return pageList;
    }

    @Override
    // 删除
    public void delete(Integer id) {
        if(roleDao.selectById(id) == null){
            throw new LearnException("用户不存在");
        }else{
            String key = String.valueOf(id);
            removeCache(key);// 删除数据之前清空缓存
            roleDao.delete(id);
        }

    }

    // 获取缓存方法
    public static Role getCache(String key){
        Role role = getCacheMap().get(key);
        return role;
    }

    // 增加缓存方法
    public static void putCache(String key,Role role){
        getCacheMap().put(key,role);
    }

    // 清空缓存方法
    public static void removeCache(String key){
        getCacheMap().remove(key);
    }

    public static Map<String, Role>getCacheMap(){
        if(cacheMap == null){
            cacheMap = new HashMap<>();
        }
        return cacheMap;
    }

}
