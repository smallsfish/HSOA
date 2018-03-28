package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.AdministrativeAttendance;
import com.hassdata.hserp.service.attendance.AttendanceService;
import com.hassdata.hserp.utils.ServerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 考勤管理
 */

@Controller
@Scope("prototype")
@RequestMapping("system/attendance/api")
@ResponseBody
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /*考勤列表管理*/
    @RequestMapping(value = "attendanceList", method = RequestMethod.GET)
    public ServerResponse attendanceList(AdministrativeAttendance administrativeAttendance, Integer page, Integer limit, String orderBy){
        if (page == null || limit == null) {
            return ServerResponse.createByErrorMessage("参数不得为空");
        }
        /*模糊查询*/
        if (administrativeAttendance.getName() != null) {
            administrativeAttendance.setName("%" + administrativeAttendance.getName() + "%");
        }
        List<AdministrativeAttendance> dataList = null;
        dataList = attendanceService.listLikePage(administrativeAttendance, "id desc", page, limit);

        long count = attendanceService.countLike(administrativeAttendance);
        return ServerResponse.createBySuccessForLayuiTable("请求成功", dataList, count);
    }


    /*id查询*/
    @RequestMapping(value = "attendanceEditById", method = RequestMethod.GET)
    public ServerResponse attendanceEditById(Integer id) {
        if (null == id || id == 0) {
            return ServerResponse.createByErrorMessage("参数不得为空");
        }

        AdministrativeAttendance dataList = (AdministrativeAttendance) attendanceService.getById(id);

        return ServerResponse.createBySuccess(dataList);
    }

    /*备注修改*/
    @RequestMapping(value = "attendanceEdit", method = RequestMethod.POST)
    public ServerResponse attendanceEdit(AdministrativeAttendance administrativeAttendance) {
        if (administrativeAttendance.getId() == null) {
            return ServerResponse.createByErrorMessage("参数不得为空");
        }
        try {
            administrativeAttendance.setRemarks(administrativeAttendance.getRemarks());
            attendanceService.update(administrativeAttendance);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

}
