package com.hassdata.hserp.po;

public class AdministrativeMeeting {
    //id
    private Integer id;

    //参与人数
    private String participateNumber;

    //应参与人数
    private String shouldparticipateNumber;

    //实参与人数
    private String realparticipateNumber;

    //会议内容
    private String content;

    //会议时间
    private String time;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取参与人数
     *
     * @return participate_number - 参与人数
     */
    public String getParticipateNumber() {
        return participateNumber;
    }

    /**
     * 设置参与人数
     *
     * @param participateNumber 参与人数
     */
    public void setParticipateNumber(String participateNumber) {
        this.participateNumber = participateNumber;
    }

    /**
     * 获取应参与人数
     *
     * @return shouldparticipate_number - 应参与人数
     */
    public String getShouldparticipateNumber() {
        return shouldparticipateNumber;
    }

    /**
     * 设置应参与人数
     *
     * @param shouldparticipateNumber 应参与人数
     */
    public void setShouldparticipateNumber(String shouldparticipateNumber) {
        this.shouldparticipateNumber = shouldparticipateNumber;
    }

    /**
     * 获取实参与人数
     *
     * @return realparticipate_number - 实参与人数
     */
    public String getRealparticipateNumber() {
        return realparticipateNumber;
    }

    /**
     * 设置实参与人数
     *
     * @param realparticipateNumber 实参与人数
     */
    public void setRealparticipateNumber(String realparticipateNumber) {
        this.realparticipateNumber = realparticipateNumber;
    }

    /**
     * 获取会议内容
     *
     * @return content - 会议内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置会议内容
     *
     * @param content 会议内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取会议时间
     *
     * @return time - 会议时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置会议时间
     *
     * @param time 会议时间
     */
    public void setTime(String time) {
        this.time = time;
    }
}