package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.CustomResourceMapper;
import com.hassdata.hserp.po.CustomResource;
import com.hassdata.hserp.service.CustomResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("customResourceService")
public class CustomResourceServiceImpl extends BaseServiceImpl<CustomResource> implements CustomResourceService {

    @Resource
    private CustomResourceMapper customResourceMapper;

    @Override
    public BaseDao<CustomResource> getMapper() {
        return this.customResourceMapper;
    }
}
