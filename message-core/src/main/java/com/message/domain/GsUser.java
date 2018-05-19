package com.message.domain;

public class GsUser {
    private Long id;

    private String gsName;

    private Integer gsNum;

    private String pws;

    private Integer tyFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName == null ? null : gsName.trim();
    }

    public Integer getGsNum() {
        return gsNum;
    }

    public void setGsNum(Integer gsNum) {
        this.gsNum = gsNum;
    }

    public String getPws() {
        return pws;
    }

    public void setPws(String pws) {
        this.pws = pws == null ? null : pws.trim();
    }

    public Integer getTyFlag() {
        return tyFlag;
    }

    public void setTyFlag(Integer tyFlag) {
        this.tyFlag = tyFlag;
    }
}