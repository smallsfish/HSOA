package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.HumanDeptMapper;
import com.hassdata.hserp.dao.HumanStoreMapper;
import com.hassdata.hserp.po.HumanDept;
import com.hassdata.hserp.po.HumanStore;
import com.hassdata.hserp.service.HumanDeptService;
import com.hassdata.hserp.service.HumanStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WTF
 * @DESCRIPTION 部门管理
 * @create 2018-02-03 10:36
 **/
@Service("humanDeptService")
public class HumanDeptServiceImpl extends BaseServiceImpl<HumanDept> implements HumanDeptService{
    @Autowired
    private HumanDeptMapper humanDeptMapper;
    @Override
    public BaseDao<HumanDept> getMapper() {
        return this.humanDeptMapper;
    }
}
