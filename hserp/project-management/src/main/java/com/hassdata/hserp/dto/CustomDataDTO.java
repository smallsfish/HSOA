package com.hassdata.hserp.dto;

public class CustomDataDTO {
    private Integer id;

    private Integer cid;

    private Integer orderNumber;

    //客户资料路径
    private String customDataWay;

    //客户资料类型
    private Byte customDataType;

    //客户资料名称
    private String customDataName;

    //客户资料信息
    private String customDataInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomDataWay() {
        return customDataWay;
    }

    public void setCustomDataWay(String customDataWay) {
        this.customDataWay = customDataWay;
    }

    public Byte getCustomDataType() {
        return customDataType;
    }

    public void setCustomDataType(Byte customDataType) {
        this.customDataType = customDataType;
    }

    public String getCustomDataName() {
        return customDataName;
    }

    public void setCustomDataName(String customDataName) {
        this.customDataName = customDataName;
    }

    public String getCustomDataInfo() {
        return customDataInfo;
    }

    public void setCustomDataInfo(String customDataInfo) {
        this.customDataInfo = customDataInfo;
    }
}
