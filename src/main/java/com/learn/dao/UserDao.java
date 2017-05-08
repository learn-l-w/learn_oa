package com.learn.dao;

import com.learn.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: wangqingyu
 * Date: 17/5/7
 * Time: 上午15:26
 */
@Mapper
public interface UserDao {

    User selectByPassword(int password);
    User selectById(int id);
}
