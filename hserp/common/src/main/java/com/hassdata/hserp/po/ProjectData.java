package com.hassdata.hserp.po;

public class ProjectData {
    private Integer id;

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
     * 获取项目资料路径
     *
     * @return project_data_way - 项目资料路径
     */
    public String getProjectDataWay() {
        return projectDataWay;
    }

    /**
     * 设置项目资料路径
     *
     * @param projectDataWay 项目资料路径
     */
    public void setProjectDataWay(String projectDataWay) {
        this.projectDataWay = projectDataWay;
    }

    /**
     * 获取项目资料名称
     *
     * @return project_data_type - 项目资料名称
     */
    public Byte getProjectDataType() {
        return projectDataType;
    }

    /**
     * 设置项目资料名称
     *
     * @param projectDataType 项目资料名称
     */
    public void setProjectDataType(Byte projectDataType) {
        this.projectDataType = projectDataType;
    }

    /**
     * 获取项目资料名称
     *
     * @return project_data_name - 项目资料名称
     */
    public String getProjectDataName() {
        return projectDataName;
    }

    /**
     * 设置项目资料名称
     *
     * @param projectDataName 项目资料名称
     */
    public void setProjectDataName(String projectDataName) {
        this.projectDataName = projectDataName;
    }

    /**
     * 获取项目资料信息
     *
     * @return project_data_info - 项目资料信息
     */
    public String getProjectDataInfo() {
        return projectDataInfo;
    }

    /**
     * 设置项目资料信息
     *
     * @param projectDataInfo 项目资料信息
     */
    public void setProjectDataInfo(String projectDataInfo) {
        this.projectDataInfo = projectDataInfo;
    }
}