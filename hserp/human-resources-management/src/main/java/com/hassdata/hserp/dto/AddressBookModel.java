package com.hassdata.hserp.dto;

import java.io.Serializable;

/**
 * @author WTF
 * @DESCRIPTION
 * @create 2018-02-05 11:29
 **/
public class AddressBookModel implements Serializable{
    //序号
    private Integer index;

    //唯一ID
    private Integer id;

    //职员ID
    private Integer eid;

    //姓名
    private String name;

    //性别
    private String sex;

    //电话号码
    private String tel;

    //部门名
    private String deptName;

    //邮箱
    private String email;

    //家庭地址
    private String address;


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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
