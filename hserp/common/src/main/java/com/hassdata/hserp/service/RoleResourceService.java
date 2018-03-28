package com.hassdata.hserp.service;


import com.hassdata.hserp.base.BaseService;
import com.hassdata.hserp.po.RoleResource;


public interface RoleResourceService extends BaseService<RoleResource> {
    int updateByRoleId(RoleResource role_resource);
    int deleteByRoleId(Integer rid);
}
