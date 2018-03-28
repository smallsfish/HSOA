package com.hassdata.hserp.dao;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.po.AdminRole;
import org.springframework.stereotype.Component;

@Component
public interface AdminRoleMapper extends BaseDao<AdminRole> {
    int updateAdminRoleByAid(AdminRole admin_role);

    int deleteAdminRoleByAid(Integer aid);
}