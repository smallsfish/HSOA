package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdminUserMapper;
import com.hassdata.hserp.po.AdminUser;
import com.hassdata.hserp.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 *
 */
@Service("adminUserService")
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserDao;


    @Override
    public BaseDao<AdminUser> getMapper() {
        return this.adminUserDao;
    }



    @Override
    public Set<String> findRoleByAccount(String account) {
        return adminUserDao.findRoleByAccount(account);
    }

    @Override
    public Set<String> findPermissionByAccount(String account) {
        return adminUserDao.findPermissionByAccount(account);
    }

    @Override
    public int getIdMax() {
        return adminUserDao.getIdMax();
    }
}
