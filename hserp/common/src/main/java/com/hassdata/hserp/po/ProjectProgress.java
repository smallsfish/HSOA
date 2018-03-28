package com.hassdata.hserp.po;

public class ProjectProgress {
    private Long id;

    //项目ID
    private Integer pid;

    //进度提交人
    private Integer cid;

    //项目进度   最大100
    private Byte precend;

    //项目进度描述
    private String descript;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
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
     * 获取进度提交人
     *
     * @return cid - 进度提交人
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置进度提交人
     *
     * @param cid 进度提交人
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取项目进度   最大100
     *
     * @return precend - 项目进度   最大100
     */
    public Byte getPrecend() {
        return precend;
    }

    /**
     * 设置项目进度   最大100
     *
     * @param precend 项目进度   最大100
     */
    public void setPrecend(Byte precend) {
        this.precend = precend;
    }

    /**
     * 获取项目进度描述
     *
     * @return descript - 项目进度描述
     */
    public String getDescript() {
        return descript;
    }

    /**
     * 设置项目进度描述
     *
     * @param descript 项目进度描述
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }
}