package com.learn.dao;


import com.learn.model.AddressBookGroup;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by 小野花 on 2017/6/5.
 */
public interface AddressBookGroupDao {
    List<AddressBookGroup> selectAll();
    AddressBookGroup selectByTitle(@QueryParam("title")String title);
    void insert(AddressBookGroup addressbookGroup);
    void update(AddressBookGroup addressBookGroup);
    void delete(int id);
}
