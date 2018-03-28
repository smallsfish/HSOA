package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.RoleMapper;
import com.hassdata.hserp.po.Role;
import com.hassdata.hserp.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Resource
    private RoleMapper roleDao;
    @Override
    public BaseDao<Role> getMapper() {
        return roleDao;
    }

    @Override
    public Integer getIdMax() {
        return roleDao.getIdMax();
    }

}
