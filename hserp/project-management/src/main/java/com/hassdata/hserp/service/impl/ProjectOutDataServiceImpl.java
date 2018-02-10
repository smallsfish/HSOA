package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.ProjectOutDataMapper;
import com.hassdata.hserp.po.ProjectOutData;
import com.hassdata.hserp.service.ProjectOutDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("projectOutDataService")
public class ProjectOutDataServiceImpl extends BaseServiceImpl<ProjectOutData> implements ProjectOutDataService {

    @Resource
    private ProjectOutDataMapper projectOutDataMapper;

    @Override
    public BaseDao<ProjectOutData> getMapper() {
        return this.projectOutDataMapper;
    }
}
