package com.message.domain;

public class MarketWithBLOBs extends Market {
    private String khLy;

    private String fpHf;

    private String firstinfo;

    public String getKhLy() {
        return khLy;
    }

    public void setKhLy(String khLy) {
        this.khLy = khLy == null ? null : khLy.trim();
    }

    public String getFpHf() {
        return fpHf;
    }

    public void setFpHf(String fpHf) {
        this.fpHf = fpHf == null ? null : fpHf.trim();
    }

    public String getFirstinfo() {
        return firstinfo;
    }

    public void setFirstinfo(String firstinfo) {
        this.firstinfo = firstinfo == null ? null : firstinfo.trim();
    }
}