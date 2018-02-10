package com.hassdata.hserp.po;

public class AdministrativeReimbursement {
    //id
    private Integer id;

    //金额
    private String amount;

    //类别
    private String category;

    //费用明细
    private String details;

    //报销图片
    private String img;

    //审批人
    private String approver;

    //抄送人
    private String ccperson;

    //时间
    private String time;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取金额
     *
     * @return Amount - 金额
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 获取类别
     *
     * @return category - 类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置类别
     *
     * @param category 类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取费用明细
     *
     * @return details - 费用明细
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置费用明细
     *
     * @param details 费用明细
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 获取报销图片
     *
     * @return img - 报销图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置报销图片
     *
     * @param img 报销图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取审批人
     *
     * @return Approver - 审批人
     */
    public String getApprover() {
        return approver;
    }

    /**
     * 设置审批人
     *
     * @param approver 审批人
     */
    public void setApprover(String approver) {
        this.approver = approver;
    }

    /**
     * 获取抄送人
     *
     * @return ccperson - 抄送人
     */
    public String getCcperson() {
        return ccperson;
    }

    /**
     * 设置抄送人
     *
     * @param ccperson 抄送人
     */
    public void setCcperson(String ccperson) {
        this.ccperson = ccperson;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(String time) {
        this.time = time;
    }
}