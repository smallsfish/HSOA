package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.ProjectStaffMapper;
import com.hassdata.hserp.po.ProjectStaff;
import com.hassdata.hserp.service.ProjectStaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("projectStaffService")
public class ProjectStaffServiceImpl extends BaseServiceImpl<ProjectStaff> implements ProjectStaffService {

    @Resource
    private ProjectStaffMapper projectStaffMapper;

    @Override
    public BaseDao<ProjectStaff> getMapper() {
        return this.projectStaffMapper;
    }
}
