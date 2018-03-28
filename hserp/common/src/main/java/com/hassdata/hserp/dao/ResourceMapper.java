package com.hassdata.hserp.dao;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.po.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResourceMapper extends BaseDao<Resource> {
    List<Resource> getResourceByAccount(String account);

    List<String> getResourceNameByRoleId(Integer rid);
}