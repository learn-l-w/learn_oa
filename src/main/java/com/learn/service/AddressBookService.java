package com.learn.service;


import com.learn.model.AddressBook;

import java.util.List;


/**
 * Created by 小野花 on 2017/5/22.
 */
public interface AddressBookService {

    List<AddressBook> selectFunction(String title,String email,String phone,String qq,String address,int sex,int time,int update_time);
    void delete(int id);
    void update(AddressBook addressBook);
}
