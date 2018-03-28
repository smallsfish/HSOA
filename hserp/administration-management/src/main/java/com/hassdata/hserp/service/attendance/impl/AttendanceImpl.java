package com.hassdata.hserp.service.attendance.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdministrativeAttendanceMapper;
import com.hassdata.hserp.po.AdministrativeAttendance;
import com.hassdata.hserp.service.attendance.AttendanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("attendanceService")
public class AttendanceImpl extends BaseServiceImpl<AdministrativeAttendance> implements AttendanceService {
    @Resource
    private AdministrativeAttendanceMapper administrativeAttendanceMapper;

    @Override
    public BaseDao<AdministrativeAttendance> getMapper() {
        return this.administrativeAttendanceMapper;
    }
}
