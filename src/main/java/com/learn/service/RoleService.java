package com.learn.service;

import com.learn.model.Role;

/**
 * Created with InterlliJ IDEA.
 * User: linfei
 * Date: 2017/5/9
 * Time: 15:08
 */
public interface RoleService {

    Role selectById(int id);
    void insert(Role role);
    void update(Role role);
    void delete(int id);
}
