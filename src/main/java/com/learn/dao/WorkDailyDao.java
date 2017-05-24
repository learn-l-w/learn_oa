package com.learn.dao;

import com.learn.model.WorkDaily;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
@Mapper
public interface WorkDailyDao {
     void insertWorkDaily(WorkDaily workDaily);
     List<WorkDaily> selectAllWorkDaily();
}
