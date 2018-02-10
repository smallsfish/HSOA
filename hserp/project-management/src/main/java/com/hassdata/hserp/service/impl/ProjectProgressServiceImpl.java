package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.ProjectProgressMapper;
import com.hassdata.hserp.po.ProjectProgress;
import com.hassdata.hserp.service.ProjectProgressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("projectProgressService")
public class ProjectProgressServiceImpl extends BaseServiceImpl<ProjectProgress> implements ProjectProgressService {

    @Resource
    private ProjectProgressMapper projectProgressMapper;

    @Override
    public BaseDao<ProjectProgress> getMapper() {
        return this.projectProgressMapper;
    }
}
