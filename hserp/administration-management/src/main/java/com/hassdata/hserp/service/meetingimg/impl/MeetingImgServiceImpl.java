package com.hassdata.hserp.service.meetingimg.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdministrativeMeetingimgMapper;
import com.hassdata.hserp.po.AdministrativeMeetingimg;
import com.hassdata.hserp.service.meetingimg.MeetingImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("meetingImgService")
public class MeetingImgServiceImpl extends BaseServiceImpl<AdministrativeMeetingimg> implements MeetingImgService {
    @Resource
    private AdministrativeMeetingimgMapper administrativeMeetingimgMapper;

    @Override
    public BaseDao<AdministrativeMeetingimg> getMapper() {
        return this.administrativeMeetingimgMapper;
    }
}
