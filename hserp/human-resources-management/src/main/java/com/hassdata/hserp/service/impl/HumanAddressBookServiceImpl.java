package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.HumanAddressBookMapper;
import com.hassdata.hserp.po.HumanAddressBook;
import com.hassdata.hserp.service.HumanAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WTF
 * @DESCRIPTION 通讯录管理
 * @create 2018-02-03 10:36
 **/
@Service("humanAddressBookService")
public class HumanAddressBookServiceImpl extends BaseServiceImpl<HumanAddressBook> implements HumanAddressBookService{
    @Autowired
    private HumanAddressBookMapper humanAddressBookMapper;
    @Override
    public BaseDao<HumanAddressBook> getMapper() {
        return this.humanAddressBookMapper;
    }
}
