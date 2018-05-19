package com.message.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.message.dao.GsUserMapper;
import com.message.domain.GsUser;
import com.message.service.ILoginOutService;

@Service
public class LoginOutServiceImpl implements ILoginOutService {

	@Resource
	private GsUserMapper gsUserMapper;
	
	@Override
	public boolean gsUserLoginCheck(String userName, String password) {
		GsUser gsUser = gsUserMapper.selectByUserName(userName);
		return password.equals(gsUser.getPws());
	}

}
