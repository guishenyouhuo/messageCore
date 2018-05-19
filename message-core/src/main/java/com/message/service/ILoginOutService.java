package com.message.service;

public interface ILoginOutService {
	
	/**
	 * 招商用户登录认证
	 * @param userName
	 * @return
	 */
	boolean gsUserLoginCheck(String userName, String password);
}
