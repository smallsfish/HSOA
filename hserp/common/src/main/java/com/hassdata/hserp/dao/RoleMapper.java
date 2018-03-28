package com.hassdata.hserp.dao;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.po.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleMapper extends BaseDao<Role> {
    Integer getIdMax();
}