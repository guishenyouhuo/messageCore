package com.message.domain;

public class MsgTemplate {
    private String tmpKey;

    private String hasFirstline;

    private String colName;

    private String colTel;

    private String colAddr;

    private String colMsg;

    public String getTmpKey() {
        return tmpKey;
    }

    public void setTmpKey(String tmpKey) {
        this.tmpKey = tmpKey == null ? null : tmpKey.trim();
    }

    public String getHasFirstline() {
        return hasFirstline;
    }

    public void setHasFirstline(String hasFirstline) {
        this.hasFirstline = hasFirstline == null ? null : hasFirstline.trim();
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName == null ? null : colName.trim();
    }

    public String getColTel() {
        return colTel;
    }

    public void setColTel(String colTel) {
        this.colTel = colTel == null ? null : colTel.trim();
    }

    public String getColAddr() {
        return colAddr;
    }

    public void setColAddr(String colAddr) {
        this.colAddr = colAddr == null ? null : colAddr.trim();
    }

    public String getColMsg() {
        return colMsg;
    }

    public void setColMsg(String colMsg) {
        this.colMsg = colMsg == null ? null : colMsg.trim();
    }
}