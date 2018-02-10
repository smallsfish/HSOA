package com.hassdata.hserp.service.data.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdministrativeDataMapper;
import com.hassdata.hserp.po.AdministrativeData;
import com.hassdata.hserp.service.data.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("dataService")
public class dataImpl extends BaseServiceImpl<AdministrativeData> implements DataService {

    @Resource
    private AdministrativeDataMapper administrativeDataMapper;

    @Override
    public BaseDao<AdministrativeData> getMapper() {
        return this.administrativeDataMapper;
    }
}
