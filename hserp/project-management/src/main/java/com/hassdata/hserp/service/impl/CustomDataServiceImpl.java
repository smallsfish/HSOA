package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.CustomDataMapper;
import com.hassdata.hserp.po.CustomData;
import com.hassdata.hserp.service.CustomDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("customDataService")
public class CustomDataServiceImpl extends BaseServiceImpl<CustomData> implements CustomDataService {

    @Resource
    private CustomDataMapper customDataMapper;

    @Override
    public BaseDao<CustomData> getMapper() {
        return this.customDataMapper;
    }
}
