package com.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.dao.GsUserMapper;
import com.message.domain.GsUser;
import com.message.service.IGsUserService;

@Service("gsUserService")
public class GsUserServiceImpl implements IGsUserService {

	@Autowired
	private GsUserMapper gsUserMapper;
	@Override
	public List<GsUser> getAllGsUsers(String type) {
		return gsUserMapper.selectGsUsersByType(type);
	}
}
