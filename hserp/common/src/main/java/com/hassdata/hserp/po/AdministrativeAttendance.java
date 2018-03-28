package com.hassdata.hserp.po;

public class AdministrativeAttendance {
    private Integer id;

    //第三方id
    private String thirdId;

    //用户ID
    private String userid;

    //位置结果（Normal:范围内；Outside:范围外；NotSigned:未打卡）
    private String locationresult;

    //姓名
    private String name;

    //上班打卡时间
    private String onTime;

    //下班打卡时间
    private String underTime;

    //时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡）
    private String timeresult;

    //特殊情况备注
    private String remarks;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取第三方id
     *
     * @return third_id - 第三方id
     */
    public String getThirdId() {
        return thirdId;
    }

    /**
     * 设置第三方id
     *
     * @param thirdId 第三方id
     */
    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * 获取用户ID
     *
     * @return userId - 用户ID
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取位置结果（Normal:范围内；Outside:范围外；NotSigned:未打卡）
     *
     * @return locationResult - 位置结果（Normal:范围内；Outside:范围外；NotSigned:未打卡）
     */
    public String getLocationresult() {
        return locationresult;
    }

    /**
     * 设置位置结果（Normal:范围内；Outside:范围外；NotSigned:未打卡）
     *
     * @param locationresult 位置结果（Normal:范围内；Outside:范围外；NotSigned:未打卡）
     */
    public void setLocationresult(String locationresult) {
        this.locationresult = locationresult;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上班打卡时间
     *
     * @return on_time - 上班打卡时间
     */
    public String getOnTime() {
        return onTime;
    }

    /**
     * 设置上班打卡时间
     *
     * @param onTime 上班打卡时间
     */
    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    /**
     * 获取下班打卡时间
     *
     * @return under_time - 下班打卡时间
     */
    public String getUnderTime() {
        return underTime;
    }

    /**
     * 设置下班打卡时间
     *
     * @param underTime 下班打卡时间
     */
    public void setUnderTime(String underTime) {
        this.underTime = underTime;
    }

    /**
     * 获取时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡）
     *
     * @return timeResult - 时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡）
     */
    public String getTimeresult() {
        return timeresult;
    }

    /**
     * 设置时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡）
     *
     * @param timeresult 时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡）
     */
    public void setTimeresult(String timeresult) {
        this.timeresult = timeresult;
    }

    /**
     * 获取特殊情况备注
     *
     * @return remarks - 特殊情况备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置特殊情况备注
     *
     * @param remarks 特殊情况备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}