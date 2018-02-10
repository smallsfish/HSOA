package com.hassdata.hserp.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author WTF
 * @DESCRIPTION
 * @create 2018-02-05 12:34
 **/
public class TreatModel implements Serializable{

    //序号
    private Integer index;

    //唯一ID
    private Integer id;

    //职员ID
    private Integer eid;

    //姓名
    private String name;

    //部门名
    private String deptName;

    //薪资
    private BigDecimal salary;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
