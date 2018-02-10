package com.hassdata.hserp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WTF
 * @DESCRIPTION
 * @create 2018-02-05 10:30
 **/
public class StoreModel implements Serializable{

    //序号
    private Integer index;

    //唯一ID
    private Integer id;

    //创建人
    private Integer createBy;

    //入职人员姓名
    private String name;

    //头像
    private String headimage;

    //性别
    private String sex;

    //电话号码
    private String tel;

    //身份证号
    private String idCard;

    //学历
    private String education;

    //部门ID
    private String deptName;

    //薪资
    private BigDecimal salary;

    //实习时间
    private String practiceTime;

    //false-实习 true-转正
    private String status;



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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public String getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(String practiceTime) {
        this.practiceTime = practiceTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
}
