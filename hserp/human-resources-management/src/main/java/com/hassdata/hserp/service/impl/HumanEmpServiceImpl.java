package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.HumanEmpMapper;
import com.hassdata.hserp.po.HumanEmp;
import com.hassdata.hserp.service.HumanEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WTF
 * @DESCRIPTION 在职人员管理
 * @create 2018-02-03 10:36
 **/
@Service("humanEmpService")
public class HumanEmpServiceImpl extends BaseServiceImpl<HumanEmp> implements HumanEmpService{
    @Autowired
    private HumanEmpMapper humanEmpMapper;
    @Override
    public BaseDao<HumanEmp> getMapper() {
        return this.humanEmpMapper;
    }
}
