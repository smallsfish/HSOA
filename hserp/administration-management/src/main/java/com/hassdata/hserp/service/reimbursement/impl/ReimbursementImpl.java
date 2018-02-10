package com.hassdata.hserp.service.reimbursement.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdministrativeReimbursementMapper;
import com.hassdata.hserp.po.AdministrativeReimbursement;
import com.hassdata.hserp.service.reimbursement.ReimbursementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("reimbursementService")
public class ReimbursementImpl extends BaseServiceImpl<AdministrativeReimbursement> implements ReimbursementService {

    @Resource
    private AdministrativeReimbursementMapper administrativeReimbursementMapper;

    @Override
    public BaseDao<AdministrativeReimbursement> getMapper() {
        return this.administrativeReimbursementMapper;
    }
}
