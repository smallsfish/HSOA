package com.hassdata.hserp.po;

public class ProjectStaff {
    private Integer id;

    //项目ID
    private Integer pid;

    //人员ID
    private Integer sid;

    //人员负责模块说明
    private String projectStaffModule;

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
     * 获取人员ID
     *
     * @return sid - 人员ID
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置人员ID
     *
     * @param sid 人员ID
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取人员负责模块说明
     *
     * @return project_staff_module - 人员负责模块说明
     */
    public String getProjectStaffModule() {
        return projectStaffModule;
    }

    /**
     * 设置人员负责模块说明
     *
     * @param projectStaffModule 人员负责模块说明
     */
    public void setProjectStaffModule(String projectStaffModule) {
        this.projectStaffModule = projectStaffModule;
    }
}