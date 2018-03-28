package com.hassdata.hserp.po;

public class ProjectResource {
    private Integer id;

    //项目ID
    private Integer pid;

    //客户ID
    private Integer cid;

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
     * 获取项目ID
     *
     * @return pid - 项目ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置项目ID
     *
     * @param pid 项目ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取客户ID
     *
     * @return cid - 客户ID
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置客户ID
     *
     * @param cid 客户ID
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }
}