package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.ResourceMapper;
import com.hassdata.hserp.service.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<com.hassdata.hserp.po.Resource> implements ResourceService {
    @Resource
    private ResourceMapper resourceDao;

    @Override
    public BaseDao<com.hassdata.hserp.po.Resource> getMapper() {
        return resourceDao;
    }

    @Override
    public List<com.hassdata.hserp.po.Resource> getResourceByAccount(String account) {
        return resourceDao.getResourceByAccount(account);
    }

    @Override
    public List<String> getResourceNameByRoleId(Integer rid) {
        return resourceDao.getResourceNameByRoleId(rid);
    }
}
