package com.message.domain;

public class MessageFilterDTO {

	// 所属用户号
	private String gsNum;
	// 留言类型
	private String msgType;
	// 下次回访时间
	private String nextVisitTime;
	public String getGsNum() {
		return gsNum;
	}
	public void setGsNum(String gsNum) {
		this.gsNum = gsNum;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getNextVisitTime() {
		return nextVisitTime;
	}
	public void setNextVisitTime(String nextVisitTime) {
		this.nextVisitTime = nextVisitTime;
	}
}
