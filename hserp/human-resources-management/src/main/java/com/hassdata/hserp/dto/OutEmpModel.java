package com.hassdata.hserp.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author WTF
 * @DESCRIPTION
 * @create 2018-02-05 11:02
 **/
public class OutEmpModel implements Serializable{
    //序号
    private Integer index;

    //唯一ID
    private Integer id;

    //职员ID
    private Integer eid;

    //离职人员姓名
    private String name;

    //头像
    private String headimage;

    //性别
    private String sex;

    //电话号码
    private String tel;

    //身份证号
    private String idCard;

    //部门名
    private String deptName;

    //学历
    private String education;

    //薪资
    private BigDecimal salary;

    //离职原因
    private String reason;

    //离职时间
    private String outTime;





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

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
