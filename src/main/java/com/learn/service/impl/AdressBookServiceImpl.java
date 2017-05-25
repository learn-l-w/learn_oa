package com.learn.service.impl;

import com.learn.dao.AdressBookDao;
import com.learn.model.AdressBook;
import com.learn.service.AdressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by 小野花 on 2017/5/22.
 */
@Service
public class AdressBookServiceImpl implements AdressBookService {

    @Autowired
    private AdressBookDao adressBookDao;

    @Override
    public List<AdressBook> selectFunction(String title,String email,String phone,String qq,String address,int sex,int time,int update_time){
        return adressBookDao.selectFunction(title,email,phone,qq,address,sex,time,update_time);
    }
    @Override
    public void delete(int id){
        adressBookDao.delete(id);
    }

    @Override
    public void update(AdressBook adressBook){
        adressBookDao.update(adressBook);
    }
}
