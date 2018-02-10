package com.hassdata.hserp.po;

public class CustomData {
    private Integer id;

    private Integer cid;

    //客户资料路径
    private String customDataWay;

    //客户资料类型
    private Byte customDataType;

    //客户资料名称
    private String customDataName;

    //客户资料信息
    private String customDataInfo;

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
     * @return cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取客户资料路径
     *
     * @return custom_data_way - 客户资料路径
     */
    public String getCustomDataWay() {
        return customDataWay;
    }

    /**
     * 设置客户资料路径
     *
     * @param customDataWay 客户资料路径
     */
    public void setCustomDataWay(String customDataWay) {
        this.customDataWay = customDataWay;
    }

    /**
     * 获取客户资料类型
     *
     * @return custom_data_type - 客户资料类型
     */
    public Byte getCustomDataType() {
        return customDataType;
    }

    /**
     * 设置客户资料类型
     *
     * @param customDataType 客户资料类型
     */
    public void setCustomDataType(Byte customDataType) {
        this.customDataType = customDataType;
    }

    /**
     * 获取客户资料名称
     *
     * @return custom_data_name - 客户资料名称
     */
    public String getCustomDataName() {
        return customDataName;
    }

    /**
     * 设置客户资料名称
     *
     * @param customDataName 客户资料名称
     */
    public void setCustomDataName(String customDataName) {
        this.customDataName = customDataName;
    }

    /**
     * 获取客户资料信息
     *
     * @return custom_data_info - 客户资料信息
     */
    public String getCustomDataInfo() {
        return customDataInfo;
    }

    /**
     * 设置客户资料信息
     *
     * @param customDataInfo 客户资料信息
     */
    public void setCustomDataInfo(String customDataInfo) {
        this.customDataInfo = customDataInfo;
    }
}