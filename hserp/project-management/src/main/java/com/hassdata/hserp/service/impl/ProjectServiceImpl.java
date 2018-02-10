package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.ProjectMapper;
import com.hassdata.hserp.po.Project;
import com.hassdata.hserp.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public BaseDao<Project> getMapper() {
        return this.projectMapper;
    }
}
