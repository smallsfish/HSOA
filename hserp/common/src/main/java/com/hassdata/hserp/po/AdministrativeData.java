package com.hassdata.hserp.po;

public class AdministrativeData {
    //id
    private Integer id;

    //附件
    private String annex;

    //文本（富文本）
    private String text;

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
     * 获取附件
     *
     * @return annex - 附件
     */
    public String getAnnex() {
        return annex;
    }

    /**
     * 设置附件
     *
     * @param annex 附件
     */
    public void setAnnex(String annex) {
        this.annex = annex;
    }

    /**
     * 获取文本（富文本）
     *
     * @return text - 文本（富文本）
     */
    public String getText() {
        return text;
    }

    /**
     * 设置文本（富文本）
     *
     * @param text 文本（富文本）
     */
    public void setText(String text) {
        this.text = text;
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