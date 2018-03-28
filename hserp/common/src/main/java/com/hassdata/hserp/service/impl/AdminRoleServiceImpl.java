package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdminRoleMapper;
import com.hassdata.hserp.po.AdminRole;
import com.hassdata.hserp.service.AdminRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("adminRoleService")
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService {
    @Resource
    private AdminRoleMapper admin_RoleDao;
    @Override
    public BaseDao<AdminRole> getMapper() {
        return admin_RoleDao;
    }

    @Override
    public int updateAdminRoleByAid(AdminRole admin_role) {
        return admin_RoleDao.updateAdminRoleByAid(admin_role);
    }

    @Override
    public int deleteAdminRoleByAid(Integer aid) {
        return admin_RoleDao.deleteAdminRoleByAid(aid);
    }
}
