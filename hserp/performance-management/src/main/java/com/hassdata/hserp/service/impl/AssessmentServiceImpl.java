package com.hassdata.hserp.service.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AssessmentMapper;
import com.hassdata.hserp.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("assessmentService")
public class AssessmentServiceImpl extends  BaseServiceImpl implements  AssessmentService{

    @Autowired
    private AssessmentMapper assessmentMapper;

    @Override
    public BaseDao getMapper() {
        return this.assessmentMapper;
    }
}
