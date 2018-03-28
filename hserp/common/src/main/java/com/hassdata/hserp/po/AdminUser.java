package com.hassdata.hserp.po;

import java.util.Date;

public class AdminUser {
    private Integer id;

    private String account;

    private String password;

    private String email;

    private String headimage;

    //管理员编号
    private String identifier;

    private Date createdatetime;

    private Date lastlogintime;

    private String remarks;

    private String salt;

    private Boolean locked;

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
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return headimage
     */
    public String getHeadimage() {
        return headimage;
    }

    /**
     * @param headimage
     */
    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    /**
     * 获取管理员编号
     *
     * @return identifier - 管理员编号
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * 设置管理员编号
     *
     * @param identifier 管理员编号
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return createdatetime
     */
    public Date getCreatedatetime() {
        return createdatetime;
    }

    /**
     * @param createdatetime
     */
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    /**
     * @return lastlogintime
     */
    public Date getLastlogintime() {
        return lastlogintime;
    }

    /**
     * @param lastlogintime
     */
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return locked
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * @param locked
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}