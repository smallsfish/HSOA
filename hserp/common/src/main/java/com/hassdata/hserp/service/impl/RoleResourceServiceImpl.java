package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.RoleResourceMapper;
import com.hassdata.hserp.po.RoleResource;
import com.hassdata.hserp.service.RoleResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("roleResourceService")
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource> implements RoleResourceService {
    @Resource
    private RoleResourceMapper roleResourceDao;
    @Override
    public BaseDao<RoleResource> getMapper() {
        return roleResourceDao;
    }


    @Override
    public int updateByRoleId(RoleResource role_resource) {
        return roleResourceDao.updateByRoleId(role_resource);
    }

    @Override
    public int deleteByRoleId(Integer rid) {
        return roleResourceDao.deleteByRoleId(rid);
    }
}
