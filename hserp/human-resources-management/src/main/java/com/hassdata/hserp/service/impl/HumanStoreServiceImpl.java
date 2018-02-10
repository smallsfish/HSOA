package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.HumanStoreMapper;
import com.hassdata.hserp.po.HumanStore;
import com.hassdata.hserp.service.HumanStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WTF
 * @DESCRIPTION 人员储备管理
 * @create 2018-02-03 10:36
 **/
@Service("humanStoreService")
public class HumanStoreServiceImpl extends BaseServiceImpl<HumanStore> implements HumanStoreService{

    @Autowired
    private HumanStoreMapper humanStoreMapper;
    @Override
    public BaseDao<HumanStore> getMapper() {
        return this.humanStoreMapper;
    }

}
