package com.learn.service;

import com.learn.model.WorkDaily;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
public interface WorkDailyService {

    void insertWorkDaily(WorkDaily workDaily);
    List<WorkDaily> selectAllWorkDaily();
}
