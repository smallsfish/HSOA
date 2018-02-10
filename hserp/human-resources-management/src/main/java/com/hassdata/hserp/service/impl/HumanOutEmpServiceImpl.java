package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.HumanOutEmpMapper;
import com.hassdata.hserp.po.HumanOutEmp;
import com.hassdata.hserp.service.HumanOutEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WTF
 * @DESCRIPTION 离职人员管理
 * @create 2018-02-03 10:36
 **/
@Service("humanOutEmpService")
public class HumanOutEmpServiceImpl extends BaseServiceImpl<HumanOutEmp> implements HumanOutEmpService{
    @Autowired
    private HumanOutEmpMapper humanOutEmpMapper;
    @Override
    public BaseDao<HumanOutEmp> getMapper() {
        return this.humanOutEmpMapper;
    }
}
