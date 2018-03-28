package com.hassdata.hserp.dao;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.po.AdminUser;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface AdminUserMapper extends BaseDao<AdminUser> {
    Set<String> findRoleByAccount(String account);

    Set<String> findPermissionByAccount(String account);

    int getIdMax();
}