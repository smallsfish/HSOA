package com.hassdata.hserp.dto;

public class ProjectOutDataDTO {
    private Integer id;

    private Integer orderNumber;

    //项目ID
    private Integer pid;

    //项目输出资料路径
    private String projectOutDataWay;

    //项目输出资料类型
    private Byte projectOutDataType;

    //项目输出资料名称
    private String projectOutDataName;

    //项目输出资料信息
    private String projectOutDataInfo;

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

    public String getProjectOutDataWay() {
        return projectOutDataWay;
    }

    public void setProjectOutDataWay(String projectOutDataWay) {
        this.projectOutDataWay = projectOutDataWay;
    }

    public Byte getProjectOutDataType() {
        return projectOutDataType;
    }

    public void setProjectOutDataType(Byte projectOutDataType) {
        this.projectOutDataType = projectOutDataType;
    }

    public String getProjectOutDataName() {
        return projectOutDataName;
    }

    public void setProjectOutDataName(String projectOutDataName) {
        this.projectOutDataName = projectOutDataName;
    }

    public String getProjectOutDataInfo() {
        return projectOutDataInfo;
    }

    public void setProjectOutDataInfo(String projectOutDataInfo) {
        this.projectOutDataInfo = projectOutDataInfo;
    }
}
