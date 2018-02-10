package com.hassdata.hserp.dto;

public class ProjectDataDTO {
    private Integer id;

    private Integer orderNumber;

    //项目ID
    private Integer pid;

    //项目资料路径
    private String projectDataWay;

    //项目资料名称
    private Byte projectDataType;

    //项目资料名称
    private String projectDataName;

    //项目资料信息
    private String projectDataInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProjectDataWay() {
        return projectDataWay;
    }

    public void setProjectDataWay(String projectDataWay) {
        this.projectDataWay = projectDataWay;
    }

    public Byte getProjectDataType() {
        return projectDataType;
    }

    public void setProjectDataType(Byte projectDataType) {
        this.projectDataType = projectDataType;
    }

    public String getProjectDataName() {
        return projectDataName;
    }

    public void setProjectDataName(String projectDataName) {
        this.projectDataName = projectDataName;
    }

    public String getProjectDataInfo() {
        return projectDataInfo;
    }

    public void setProjectDataInfo(String projectDataInfo) {
        this.projectDataInfo = projectDataInfo;
    }
}
