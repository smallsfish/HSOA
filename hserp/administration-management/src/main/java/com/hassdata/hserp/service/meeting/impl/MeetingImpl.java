package com.hassdata.hserp.service.meeting.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdministrativeMeetingMapper;
import com.hassdata.hserp.po.AdministrativeMeeting;
import com.hassdata.hserp.service.meeting.MeetingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("meetingService")
public class MeetingImpl extends BaseServiceImpl<AdministrativeMeeting> implements MeetingService {
    @Resource
    private AdministrativeMeetingMapper administrativeMeetingMapper;

    @Override
    public BaseDao<AdministrativeMeeting> getMapper() {
        return this.administrativeMeetingMapper;
    }
}
