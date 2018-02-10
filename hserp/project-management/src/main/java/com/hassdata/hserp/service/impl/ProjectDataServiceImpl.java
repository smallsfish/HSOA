package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.ProjectDataMapper;
import com.hassdata.hserp.po.ProjectData;
import com.hassdata.hserp.service.ProjectDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("projectDataService")
public class ProjectDataServiceImpl extends BaseServiceImpl<ProjectData> implements ProjectDataService {

    @Resource
    private ProjectDataMapper projectDataMapper;

    @Override
    public BaseDao<ProjectData> getMapper() {
        return this.projectDataMapper;
    }
}
