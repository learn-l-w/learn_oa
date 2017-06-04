package com.learn.service;

import com.learn.model.Role;
import com.learn.model.base.PageList;

/**
 * Created with InterlliJ IDEA.
 * User: linfei
 * Date: 2017/5/9
 * Time: 15:08
 */
public interface RoleService {

    Role selectById(Integer id);
    void insert(Role role);
    void update(Role role);
    void delete(Integer id);
    PageList<Role> getPage(int offset, int length);
}
