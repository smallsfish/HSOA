package com.hassdata.hserp.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CustomResource {
    private Integer id;

    //公司名称
    private String company;

    //公司规模
    private Byte size;

    //客户公司负责人
    private String responsiblePerson;

    //客户公司领导
    private String lead;

    //客户状态
    private Byte status;

    //公司电话
    private String companyTel;

    //公司E-mail
    private String companyEamil;

    //公司联系人电话
    private String responsibleTel;

    //公司联系人E-mail
    private String responsibleEmail;

    //公司创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date companyTime;

    //公司联系人生日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date responsibleBirthday;

    //客户资源收录时间
    private Date createTime;

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
     * 获取公司名称
     *
     * @return company - 公司名称
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置公司名称
     *
     * @param company 公司名称
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取公司规模
     *
     * @return size - 公司规模
     */
    public Byte getSize() {
        return size;
    }

    /**
     * 设置公司规模
     *
     * @param size 公司规模
     */
    public void setSize(Byte size) {
        this.size = size;
    }

    /**
     * 获取客户公司负责人
     *
     * @return responsible_person - 客户公司负责人
     */
    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    /**
     * 设置客户公司负责人
     *
     * @param responsiblePerson 客户公司负责人
     */
    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    /**
     * 获取客户公司领导
     *
     * @return lead - 客户公司领导
     */
    public String getLead() {
        return lead;
    }

    /**
     * 设置客户公司领导
     *
     * @param lead 客户公司领导
     */
    public void setLead(String lead) {
        this.lead = lead;
    }

    /**
     * 获取客户状态
     *
     * @return status - 客户状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置客户状态
     *
     * @param status 客户状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取公司电话
     *
     * @return company_tel - 公司电话
     */
    public String getCompanyTel() {
        return companyTel;
    }

    /**
     * 设置公司电话
     *
     * @param companyTel 公司电话
     */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    /**
     * 获取公司E-mail
     *
     * @return company_eamil - 公司E-mail
     */
    public String getCompanyEamil() {
        return companyEamil;
    }

    /**
     * 设置公司E-mail
     *
     * @param companyEamil 公司E-mail
     */
    public void setCompanyEamil(String companyEamil) {
        this.companyEamil = companyEamil;
    }

    /**
     * 获取公司联系人电话
     *
     * @return responsible_tel - 公司联系人电话
     */
    public String getResponsibleTel() {
        return responsibleTel;
    }

    /**
     * 设置公司联系人电话
     *
     * @param responsibleTel 公司联系人电话
     */
    public void setResponsibleTel(String responsibleTel) {
        this.responsibleTel = responsibleTel;
    }

    /**
     * 获取公司联系人E-mail
     *
     * @return responsible_email - 公司联系人E-mail
     */
    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    /**
     * 设置公司联系人E-mail
     *
     * @param responsibleEmail 公司联系人E-mail
     */
    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    /**
     * 获取公司创建时间
     *
     * @return company_time - 公司创建时间
     */
    public Date getCompanyTime() {
        return companyTime;
    }

    /**
     * 设置公司创建时间
     *
     * @param companyTime 公司创建时间
     */
    public void setCompanyTime(Date companyTime) {
        this.companyTime = companyTime;
    }

    /**
     * 获取公司联系人生日
     *
     * @return responsible_birthday - 公司联系人生日
     */
    public Date getResponsibleBirthday() {
        return responsibleBirthday;
    }

    /**
     * 设置公司联系人生日
     *
     * @param responsibleBirthday 公司联系人生日
     */
    public void setResponsibleBirthday(Date responsibleBirthday) {
        this.responsibleBirthday = responsibleBirthday;
    }

    /**
     * 获取客户资源收录时间
     *
     * @return create_time - 客户资源收录时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置客户资源收录时间
     *
     * @param createTime 客户资源收录时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}