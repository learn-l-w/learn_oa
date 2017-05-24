package com.learn.dao;

import com.learn.model.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by 小野花 on 2017/5/22.
 */
@Mapper
public interface AddressBookDao {

    List<AddressBook> selectFunction(@Param("title") String title,@Param("email") String email,@Param("phone") String phone,@Param("qq") String qq,@Param("address") String address,@Param("sex") int sex,@Param("time") int time,@Param("update_time") int update_time);
    int delete(int id);
    void update(AddressBook addressBook);
}
