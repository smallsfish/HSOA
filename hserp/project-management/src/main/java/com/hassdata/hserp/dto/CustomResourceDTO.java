package com.hassdata.hserp.dto;

public class CustomResourceDTO {
    private Integer id;

    //序号
    private Integer orderNumber;

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
    private String companyTime;

    //公司联系人生日
    private String responsibleBirthday;

    //客户资源收录时间
    private String createTime;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Byte getSize() {
        return size;
    }

    public void setSize(Byte size) {
        this.size = size;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyEamil() {
        return companyEamil;
    }

    public void setCompanyEamil(String companyEamil) {
        this.companyEamil = companyEamil;
    }

    public String getResponsibleTel() {
        return responsibleTel;
    }

    public void setResponsibleTel(String responsibleTel) {
        this.responsibleTel = responsibleTel;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public String getCompanyTime() {
        return companyTime;
    }

    public void setCompanyTime(String companyTime) {
        this.companyTime = companyTime;
    }

    public String getResponsibleBirthday() {
        return responsibleBirthday;
    }

    public void setResponsibleBirthday(String responsibleBirthday) {
        this.responsibleBirthday = responsibleBirthday;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
