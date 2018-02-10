package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.ProjectResourceMapper;
import com.hassdata.hserp.po.ProjectResource;
import com.hassdata.hserp.service.ProjectResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("projectResourceService")
public class ProjectResourceServiceImpl extends BaseServiceImpl<ProjectResource> implements ProjectResourceService {

    @Resource
    private ProjectResourceMapper projectResourceMapper;

    @Override
    public BaseDao<ProjectResource> getMapper() {
        return this.projectResourceMapper;
    }
}
