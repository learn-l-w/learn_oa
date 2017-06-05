package com.learn.service.impl;

import com.learn.dao.AddressBookGroupDao;
import com.learn.model.AddressBookGroup;
import com.learn.model.User;
import com.learn.service.AddressBookGroupService;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 小野花 on 2017/6/5.
 */
@Service
public class AddressBookGroupServiceImpl implements AddressBookGroupService {

    @Autowired
    private AddressBookGroupDao addressBookGroupDao;

    @Override
    public List<AddressBookGroup> selectAll() {

      return  addressBookGroupDao.selectAll();

    }

    @Override
    public AddressBookGroup selectByTitle(String title){
        return addressBookGroupDao.selectByTitle(title);
    }

    @Override
    public void insert(AddressBookGroup addressBookGroup){
        addressBookGroupDao.insert(addressBookGroup);
    }

    @Override
    public void update(AddressBookGroup addressBookGroup){
        addressBookGroupDao.update(addressBookGroup);
    }

    @Override
    public void delete(int id){
        addressBookGroupDao.delete(id);
    }
}
