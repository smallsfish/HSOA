package com.hassdata.hserp.dto;


public class ProjectDTO {
    private Integer id;

    private Integer orderNumber;

    //项目名称
    private String projectName;

    //项目预计完工时间
    private String projectFinishDate;

    //项目周期
    private String projectRange;

    //项目创建时间
    private String projectCreateTime;

    //项目难度等级
    private Byte projectLevel;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectFinishDate() {
        return projectFinishDate;
    }

    public void setProjectFinishDate(String projectFinishDate) {
        this.projectFinishDate = projectFinishDate;
    }

    public String getProjectRange() {
        return projectRange;
    }

    public void setProjectRange(String projectRange) {
        this.projectRange = projectRange;
    }

    public String getProjectCreateTime() {
        return projectCreateTime;
    }

    public void setProjectCreateTime(String projectCreateTime) {
        this.projectCreateTime = projectCreateTime;
    }

    public Byte getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Byte projectLevel) {
        this.projectLevel = projectLevel;
    }
}
