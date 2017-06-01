package com.learn.service.impl;

import com.learn.dao.AddressBookDao;
import com.learn.model.AddressBook;
import com.learn.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by 小野花 on 2017/5/22.
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookDao addressBookDao;

    @Override
    public List<AddressBook> selectFunction(String title,String email,String phone,String qq,String address,int sex,int time,int update_time){
        return addressBookDao.selectFunction(title,email,phone,qq,address,sex,time,update_time);
    }
    @Override
    public void delete(int id){
        addressBookDao.delete(id);
    }

    @Override
    public void update(AddressBook addressBook){
        addressBookDao.update(addressBook);
    }
}
