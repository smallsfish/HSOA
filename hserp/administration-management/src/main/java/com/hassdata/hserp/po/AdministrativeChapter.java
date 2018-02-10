package com.hassdata.hserp.po;

public class AdministrativeChapter {
    //id
    private Integer id;

    //使用人
    private String user;

    //使用明细
    private String usedetails;

    //使用时间
    private String usetime;

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
     * 获取使用人
     *
     * @return user - 使用人
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置使用人
     *
     * @param user 使用人
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 获取使用明细
     *
     * @return usedetails - 使用明细
     */
    public String getUsedetails() {
        return usedetails;
    }

    /**
     * 设置使用明细
     *
     * @param usedetails 使用明细
     */
    public void setUsedetails(String usedetails) {
        this.usedetails = usedetails;
    }

    /**
     * 获取使用时间
     *
     * @return usetime - 使用时间
     */
    public String getUsetime() {
        return usetime;
    }

    /**
     * 设置使用时间
     *
     * @param usetime 使用时间
     */
    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }
}