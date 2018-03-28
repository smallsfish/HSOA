package com.hassdata.hserp.service;

import com.hassdata.hserp.base.BaseService;
import com.hassdata.hserp.po.Role;


public interface RoleService extends BaseService<Role> {
    Integer getIdMax();
}
