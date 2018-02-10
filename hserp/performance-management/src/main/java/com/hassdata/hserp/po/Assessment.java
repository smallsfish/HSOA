package com.hassdata.hserp.po;

public class Assessment {
    private Integer id;

    //被评论人id
    private Integer userId;

    //被评论人姓名
    private String userName;

    //评论人id
    private Integer assessmentId;

    //基础
    private Byte basics;

    //输出技术类文档
    private Byte technology;

    //输出非技术文档
    private Byte nontechnology;

    //客户满意
    private Byte customer;

    //行为效率
    private Byte behaviorquality;

    //开发效率
    private Byte development;

    //稳定性
    private Byte comprehensive;

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
     * 获取被评论人id
     *
     * @return user_id - 被评论人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置被评论人id
     *
     * @param userId 被评论人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取被评论人姓名
     *
     * @return user_name - 被评论人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置被评论人姓名
     *
     * @param userName 被评论人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取评论人id
     *
     * @return assessment_id - 评论人id
     */
    public Integer getAssessmentId() {
        return assessmentId;
    }

    /**
     * 设置评论人id
     *
     * @param assessmentId 评论人id
     */
    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    /**
     * 获取基础
     *
     * @return basics - 基础
     */
    public Byte getBasics() {
        return basics;
    }

    /**
     * 设置基础
     *
     * @param basics 基础
     */
    public void setBasics(Byte basics) {
        this.basics = basics;
    }

    /**
     * 获取输出技术类文档
     *
     * @return technology - 输出技术类文档
     */
    public Byte getTechnology() {
        return technology;
    }

    /**
     * 设置输出技术类文档
     *
     * @param technology 输出技术类文档
     */
    public void setTechnology(Byte technology) {
        this.technology = technology;
    }

    /**
     * 获取输出非技术文档
     *
     * @return nontechnology - 输出非技术文档
     */
    public Byte getNontechnology() {
        return nontechnology;
    }

    /**
     * 设置输出非技术文档
     *
     * @param nontechnology 输出非技术文档
     */
    public void setNontechnology(Byte nontechnology) {
        this.nontechnology = nontechnology;
    }

    /**
     * 获取客户满意
     *
     * @return customer - 客户满意
     */
    public Byte getCustomer() {
        return customer;
    }

    /**
     * 设置客户满意
     *
     * @param customer 客户满意
     */
    public void setCustomer(Byte customer) {
        this.customer = customer;
    }

    /**
     * 获取行为效率
     *
     * @return behaviorquality - 行为效率
     */
    public Byte getBehaviorquality() {
        return behaviorquality;
    }

    /**
     * 设置行为效率
     *
     * @param behaviorquality 行为效率
     */
    public void setBehaviorquality(Byte behaviorquality) {
        this.behaviorquality = behaviorquality;
    }

    /**
     * 获取开发效率

     *
     * @return development - 开发效率

     */
    public Byte getDevelopment() {
        return development;
    }

    /**
     * 设置开发效率

     *
     * @param development 开发效率

     */
    public void setDevelopment(Byte development) {
        this.development = development;
    }

    /**
     * 获取稳定性
     *
     * @return comprehensive - 稳定性
     */
    public Byte getComprehensive() {
        return comprehensive;
    }

    /**
     * 设置稳定性
     *
     * @param comprehensive 稳定性
     */
    public void setComprehensive(Byte comprehensive) {
        this.comprehensive = comprehensive;
    }
}