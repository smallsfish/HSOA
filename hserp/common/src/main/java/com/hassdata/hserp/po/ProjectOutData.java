package com.hassdata.hserp.po;

public class ProjectOutData {
    private Integer id;

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
     * 获取项目ID
     *
     * @return pid - 项目ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置项目ID
     *
     * @param pid 项目ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取项目输出资料路径
     *
     * @return project_out_data_way - 项目输出资料路径
     */
    public String getProjectOutDataWay() {
        return projectOutDataWay;
    }

    /**
     * 设置项目输出资料路径
     *
     * @param projectOutDataWay 项目输出资料路径
     */
    public void setProjectOutDataWay(String projectOutDataWay) {
        this.projectOutDataWay = projectOutDataWay;
    }

    /**
     * 获取项目输出资料类型
     *
     * @return project_out_data_type - 项目输出资料类型
     */
    public Byte getProjectOutDataType() {
        return projectOutDataType;
    }

    /**
     * 设置项目输出资料类型
     *
     * @param projectOutDataType 项目输出资料类型
     */
    public void setProjectOutDataType(Byte projectOutDataType) {
        this.projectOutDataType = projectOutDataType;
    }

    /**
     * 获取项目输出资料名称
     *
     * @return project_out_data_name - 项目输出资料名称
     */
    public String getProjectOutDataName() {
        return projectOutDataName;
    }

    /**
     * 设置项目输出资料名称
     *
     * @param projectOutDataName 项目输出资料名称
     */
    public void setProjectOutDataName(String projectOutDataName) {
        this.projectOutDataName = projectOutDataName;
    }

    /**
     * 获取项目输出资料信息
     *
     * @return project_out_data_info - 项目输出资料信息
     */
    public String getProjectOutDataInfo() {
        return projectOutDataInfo;
    }

    /**
     * 设置项目输出资料信息
     *
     * @param projectOutDataInfo 项目输出资料信息
     */
    public void setProjectOutDataInfo(String projectOutDataInfo) {
        this.projectOutDataInfo = projectOutDataInfo;
    }
}