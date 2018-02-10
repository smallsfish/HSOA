package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.HumanTreatMapper;
import com.hassdata.hserp.po.HumanTreat;
import com.hassdata.hserp.service.HumanTreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WTF
 * @DESCRIPTION 待遇管理
 * @create 2018-02-03 10:36
 **/
@Service("humanTreatService")
public class HumanTreatServiceImpl extends BaseServiceImpl<HumanTreat> implements HumanTreatService{
    @Autowired
    private HumanTreatMapper humanTreatMapper;
    @Override
    public BaseDao<HumanTreat> getMapper() {
        return this.humanTreatMapper;
    }
}
