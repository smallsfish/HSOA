package com.hassdata.hserp.dto;

public class ProjectStaffDTO {

    private Integer orderNumber;

    private Integer id;

    //项目ID
    private Integer pid;

    //人员
    private String sid;

    //人员负责模块说明
    private String projectStaffModule;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getProjectStaffModule() {
        return projectStaffModule;
    }

    public void setProjectStaffModule(String projectStaffModule) {
        this.projectStaffModule = projectStaffModule;
    }
}
