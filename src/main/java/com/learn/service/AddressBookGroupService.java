package com.learn.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.model.AddressBookGroup;

import java.util.List;

/**
 * Created by 小野花 on 2017/6/5.
 */
public interface AddressBookGroupService {
    List<AddressBookGroup> selectAll();
    AddressBookGroup selectByTitle( String title);
    void insert(AddressBookGroup addressBookGroup);
    void update(AddressBookGroup addressBookGroup);
    void delete(int id);
}
