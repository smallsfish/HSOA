package com.hassdata.hserp.service;

import com.hassdata.hserp.base.BaseService;
import com.hassdata.hserp.po.Resource;

import java.util.List;

public interface ResourceService extends BaseService<Resource> {
    List<Resource> getResourceByAccount(String account);
    List<String> getResourceNameByRoleId(Integer rid);
}
