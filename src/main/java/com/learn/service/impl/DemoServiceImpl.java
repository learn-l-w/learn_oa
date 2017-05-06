package com.learn.service.impl;

import com.learn.dao.DemoDao;
import com.learn.model.Demo;
import com.learn.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/6
 * Time: 上午10:30
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDao demoDao;

    @Override
    public Demo selectById(int id) {
        return demoDao.selectById(id);
    }
}
