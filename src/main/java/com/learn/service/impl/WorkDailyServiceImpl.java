package com.learn.service.impl;

import com.learn.dao.WorkDailyDao;
import com.learn.model.WorkDaily;
import com.learn.service.WorkDailyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangQingyu on 2017/5/20.
 */
@Service
public class WorkDailyServiceImpl implements WorkDailyService {

    @Autowired
    private WorkDailyDao wdDao;

    @Override
    public void insertWorkDaily(WorkDaily workDaily) {
        wdDao.insertWorkDaily(workDaily);
    }

    @Override
    public List<WorkDaily> selectAllWorkDaily() {
        return wdDao.selectAllWorkDaily();
    }
}
