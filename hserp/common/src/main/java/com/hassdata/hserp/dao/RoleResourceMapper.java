package com.hassdata.hserp.dao;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.po.RoleResource;
import org.springframework.stereotype.Component;

@Component
public interface RoleResourceMapper extends BaseDao<RoleResource> {
    int updateByRoleId(RoleResource role_resource);

    int deleteByRoleId(Integer rid);
}