package com.message.domain;

public class Khmessage {
    private Long id;

    private String khName;

    private String khTel;

    private String khAddress;

    private Integer fpUser;

    private String intime;

    private String lasthf;

    private Integer type;

    private String tag;

    private Integer lastUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName == null ? null : khName.trim();
    }

    public String getKhTel() {
        return khTel;
    }

    public void setKhTel(String khTel) {
        this.khTel = khTel == null ? null : khTel.trim();
    }

    public String getKhAddress() {
        return khAddress;
    }

    public void setKhAddress(String khAddress) {
        this.khAddress = khAddress == null ? null : khAddress.trim();
    }

    public Integer getFpUser() {
        return fpUser;
    }

    public void setFpUser(Integer fpUser) {
        this.fpUser = fpUser;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime == null ? null : intime.trim();
    }

    public String getLasthf() {
        return lasthf;
    }

    public void setLasthf(String lasthf) {
        this.lasthf = lasthf == null ? null : lasthf.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getLastUser() {
        return lastUser;
    }

    public void setLastUser(Integer lastUser) {
        this.lastUser = lastUser;
    }
}