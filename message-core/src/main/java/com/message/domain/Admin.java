package com.message.domain;

public class Admin {
    private Long id;

    private String adminname;

    private String adminpass;

    private String varGs;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getAdminpass() {
        return adminpass;
    }

    public void setAdminpass(String adminpass) {
        this.adminpass = adminpass == null ? null : adminpass.trim();
    }

    public String getVarGs() {
        return varGs;
    }

    public void setVarGs(String varGs) {
        this.varGs = varGs == null ? null : varGs.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}