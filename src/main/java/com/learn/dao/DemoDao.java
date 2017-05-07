package com.learn.dao;

import com.learn.model.Demo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/6
 * Time: 上午10:30
 */
@Mapper
public interface DemoDao {

    Demo selectById(int id);

    int insert(Demo demo);

    int update(Demo demo);

    int delete(int id);
}
