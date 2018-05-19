package com.message.domain;

public class TagMapping {
    private String tagId;

    private String tmpKey;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    public String getTmpKey() {
        return tmpKey;
    }

    public void setTmpKey(String tmpKey) {
        this.tmpKey = tmpKey == null ? null : tmpKey.trim();
    }
}