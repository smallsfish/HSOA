package com.hassdata.hserp.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HumanStore {
    //唯一ID
    private Integer id;

    //创建人
    private Integer createBy;

    //入职人员姓名
    private String name;

    //头像
    private String headimage;

    //性别
    private Boolean sex;

    //电话号码
    private String tel;

    //身份证号
    private String idCard;

    //学历
    private String education;

    //部门ID
    private Integer deptId;

    //薪资
    private BigDecimal salary;

    //入职时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date practiceTime;

    //0-实习 1-转正
    private Integer status;

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
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取入职人员姓名
     *
     * @return name - 入职人员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置入职人员姓名
     *
     * @param name 入职人员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取头像
     *
     * @return headimage - 头像
     */
    public String getHeadimage() {
        return headimage;
    }

    /**
     * 设置头像
     *
     * @param headimage 头像
     */
    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 获取电话号码
     *
     * @return tel - 电话号码
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话号码
     *
     * @param tel 电话号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取身份证号
     *
     * @return id_card - 身份证号
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号
     *
     * @param idCard 身份证号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
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

    /**
     * 获取入职时间
     *
     * @return practice_time - 入职时间
     */
    public Date getPracticeTime() {
        return practiceTime;
    }

    /**
     * 设置入职时间
     *
     * @param practiceTime 入职时间
     */
    public void setPracticeTime(Date practiceTime) {
        this.practiceTime = practiceTime;
    }

    /**
     * 获取0-实习 1-转正
     *
     * @return status - 0-实习 1-转正
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0-实习 1-转正
     *
     * @param status 0-实习 1-转正
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}