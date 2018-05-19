package com.message.domain;

import java.util.Date;

public class LoginRecordDetail {
    private Integer recordId;

    private String loginNum;

    private String loginUser;

    private String loginIp;

    private Date loginTime;
    
    private String loginCountry;
    
    private String loginProvince;
    
    private String loginCity;
    
    private String loginCounty;
    
    private String loginIsp;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum == null ? null : loginNum.trim();
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser == null ? null : loginUser.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

	public String getLoginCountry() {
		return loginCountry;
	}

	public void setLoginCountry(String loginCountry) {
		this.loginCountry = loginCountry;
	}

	public String getLoginProvince() {
		return loginProvince;
	}

	public void setLoginProvince(String loginProvince) {
		this.loginProvince = loginProvince;
	}

	public String getLoginCity() {
		return loginCity;
	}

	public void setLoginCity(String loginCity) {
		this.loginCity = loginCity;
	}

	public String getLoginCounty() {
		return loginCounty;
	}

	public void setLoginCounty(String loginCounty) {
		this.loginCounty = loginCounty;
	}

	public String getLoginIsp() {
		return loginIsp;
	}

	public void setLoginIsp(String loginIsp) {
		this.loginIsp = loginIsp;
	}
}