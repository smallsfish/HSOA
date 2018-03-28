package com.hassdata.hserp.service;


import com.hassdata.hserp.base.BaseService;
import com.hassdata.hserp.po.AdminRole;


public interface AdminRoleService extends BaseService<AdminRole> {
    int updateAdminRoleByAid(AdminRole admin_role);
    int deleteAdminRoleByAid(Integer aid);
}
