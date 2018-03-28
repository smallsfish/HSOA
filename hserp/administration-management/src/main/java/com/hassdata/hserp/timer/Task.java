package com.hassdata.hserp.timer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hassdata.hserp.po.AdministrativeAttendance;
import com.hassdata.hserp.service.attendance.AttendanceService;
import com.hassdata.hserp.util.Attendance;
import com.hassdata.hserp.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Task {

    @Resource
    private HttpClientUtil httpClientUtil;

    @Autowired
    private AttendanceService attendanceService;

    public String task() throws Exception {
        try {
            //获取唯一的access_token
            String dataToken = httpClientUtil.httpGetResponse("https://oapi.dingtalk.com/gettoken?corpid=ding5d7e5dda106e129835c2f4657eb6378f&corpsecret=ab7-0QR6h2PBRHCoxBRdEzMZYh00513Ljd_FLuTqKc5IftKBLhR9GF8AWJPupfPP");
            JSONObject jsonObject = JSONObject.parseObject(dataToken);
            if (jsonObject.getString("errcode").equals("40014")) {
                return "程序异常";
            }
            String access_token = jsonObject.getString("access_token").toString();

            //获取员工姓名
            String userNameList = httpClientUtil.httpGetResponse("https://oapi.dingtalk.com/user/simplelist?access_token=" + access_token + "&department_id=61598164");
            JSONObject jsonObject2 = JSONObject.parseObject(userNameList);
            if (jsonObject2.getString("errcode").equals("40014")) {
                return "程序异常";
            }

            AdministrativeAttendance administrativeAttendancea = new AdministrativeAttendance();
            JSONArray userNameArray = jsonObject2.getJSONArray("userlist");

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, Object> data = new HashMap<>();
            data.put("workDateFrom", df.format(new Date()) + " 9:00:00,");
            data.put("workDateTo", df.format(new Date()) + " 18:30:00,");
            data.put("limit", "2,");
            data.put("offset", "0,");

            //获取考勤数
            for (int i = 0; i < userNameArray.size(); i++) {
                data.put("userIdList", "[" + userNameArray.getJSONObject(i).getString("userid") + "]");
                String str = httpClientUtil.httpPostResponse("https://oapi.dingtalk.com/attendance/list?access_token=" + access_token, data);
                JSONObject jsonObject1 = JSONObject.parseObject(str);
                if (jsonObject1.getString("errcode").equals("40014")) {
                    return "程序异常";
                }
                JSONArray recordResultArray = jsonObject1.getJSONArray("recordresult");

                administrativeAttendancea.setUserid(userNameArray.getJSONObject(i).getString("userid"));
                administrativeAttendancea.setName(userNameArray.getJSONObject(i).getString("name"));

                if (recordResultArray.size() != 0) {
                    administrativeAttendancea.setLocationresult(recordResultArray.getJSONObject(0).getString("locationResult"));
                    if (recordResultArray.getJSONObject(0).getString("checkType").equals("OnDuty")) {
                        administrativeAttendancea.setOnTime(Attendance.stampToDate(recordResultArray.getJSONObject(0).getString("userCheckTime")));
                        administrativeAttendancea.setTimeresult(recordResultArray.getJSONObject(0).getString("timeResult"));
                        attendanceService.save(administrativeAttendancea);
                    }
                    if (recordResultArray.getJSONObject(0).getString("checkType").equals("OffDuty")) {
                        administrativeAttendancea.setTimeresult(recordResultArray.getJSONObject(0).getString("userCheckTime"));
                        administrativeAttendancea.setUnderTime(recordResultArray.getJSONObject(0).getString("timeResult"));
                        attendanceService.update(administrativeAttendancea);
                    }
                } else {
                    administrativeAttendancea.setOnTime("");
                    administrativeAttendancea.setUnderTime("");
                    administrativeAttendancea.setLocationresult("");
                    administrativeAttendancea.setTimeresult("");
                    attendanceService.save(administrativeAttendancea);
                }
            }
            return "添加成功";
        } catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------");
        }
        return "添加失败";
    }
}