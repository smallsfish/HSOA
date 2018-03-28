package com.hassdata.hserp.po;

import java.math.BigDecimal;

public class HumanTreat {
    //唯一ID
    private Integer id;

    private Integer eid;

    //姓名
    private String name;

    //部门ID
    private Integer deptId;

    //薪资
    private BigDecimal salary;

    /**
     * 获取唯一ID
     *
     * @return id - 唯一ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一ID
     *
     * @param id 唯一ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return eid
     */
    public Integer getEid() {
        return eid;
    }

    /**
     * @param eid
     */
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取部门ID
     *
     * @return dept_id - 部门ID
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取薪资
     *
     * @return salary - 薪资
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * 设置薪资
     *
     * @param salary 薪资
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}