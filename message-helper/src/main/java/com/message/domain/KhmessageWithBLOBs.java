package com.message.domain;

public class KhmessageWithBLOBs extends Khmessage {
    private String khLy;

    private String fpHf;

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
}