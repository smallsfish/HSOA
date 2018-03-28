package com.hassdata.hserp.service;


import com.hassdata.hserp.base.BaseService;
import com.hassdata.hserp.po.AdminUser;

import java.util.Set;

public interface AdminUserService extends BaseService<AdminUser> {
    Set<String> findRoleByAccount(String account);
    Set<String> findPermissionByAccount(String account);
    int getIdMax();
}
