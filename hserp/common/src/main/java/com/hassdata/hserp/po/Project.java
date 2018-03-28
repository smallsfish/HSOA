package com.hassdata.hserp.po;

import java.util.Date;

public class Project {
    private Integer id;

    //项目名称
    private String projectName;

    //项目预计完工时间
    private Date projectFinishDate;

    //项目周期
    private String projectRange;

    //项目创建时间
    private Date projectCreateTime;

    //项目难度等级
    private Byte projectLevel;

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
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取项目预计完工时间
     *
     * @return project_finish_date - 项目预计完工时间
     */
    public Date getProjectFinishDate() {
        return projectFinishDate;
    }

    /**
     * 设置项目预计完工时间
     *
     * @param projectFinishDate 项目预计完工时间
     */
    public void setProjectFinishDate(Date projectFinishDate) {
        this.projectFinishDate = projectFinishDate;
    }

    /**
     * 获取项目周期
     *
     * @return project_range - 项目周期
     */
    public String getProjectRange() {
        return projectRange;
    }

    /**
     * 设置项目周期
     *
     * @param projectRange 项目周期
     */
    public void setProjectRange(String projectRange) {
        this.projectRange = projectRange;
    }

    /**
     * 获取项目创建时间
     *
     * @return project_create_time - 项目创建时间
     */
    public Date getProjectCreateTime() {
        return projectCreateTime;
    }

    /**
     * 设置项目创建时间
     *
     * @param projectCreateTime 项目创建时间
     */
    public void setProjectCreateTime(Date projectCreateTime) {
        this.projectCreateTime = projectCreateTime;
    }

    /**
     * 获取项目难度等级
     *
     * @return project_level - 项目难度等级
     */
    public Byte getProjectLevel() {
        return projectLevel;
    }

    /**
     * 设置项目难度等级
     *
     * @param projectLevel 项目难度等级
     */
    public void setProjectLevel(Byte projectLevel) {
        this.projectLevel = projectLevel;
    }
}