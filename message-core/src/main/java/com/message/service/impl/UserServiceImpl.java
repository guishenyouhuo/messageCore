package com.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.message.dao.AdminMapper;
import com.message.dao.GsUserMapper;
import com.message.domain.Admin;
import com.message.domain.GsUser;
import com.message.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private GsUserMapper gsUserMapper;
	@Resource
	private AdminMapper adminMapper;
	
	@Override
	public List<GsUser> queryGsUsers(String type) {
		logger.debug("查询招商用户, type: {}", type);
		return gsUserMapper.selectGsUsers(type);
	}

	@Override
	public GsUser getUserByNum(String userNum) {
		logger.debug("根据用户号查询招商用户, userNum: {}", userNum);
		return gsUserMapper.selectGsUserByNum(userNum);
	}

	@Override
	public List<GsUser> getAllGsUsers(String type) {
		logger.debug("根据类型查询招商用户, type: {}", type);
		return gsUserMapper.selectGsUsersByType(type);
	}

	@Override
	public GsUser getUserByName(String userName) {
		logger.debug("根据用户名查询招商用户, userName: {}", userName);
		return gsUserMapper.selectByUserName(userName);
	}

	@Override
	public Admin getAdminByName(String adminName) {
		logger.info("根据管理员用户名查询管理员账户, adminName: {}", adminName);
		return adminMapper.selectByAdminName(adminName);
	}

	@Override
	public int addUser(GsUser gsUser) {
		logger.info("新增招商用户, name: {}", gsUser.getGsName());
		return gsUserMapper.insertSelective(gsUser);
	}

	@Override
	public int modifyUser(GsUser gsUser) {
		logger.info("修改招商用户, name: {}", gsUser.getGsName());
		return gsUserMapper.updateByPrimaryKeySelective(gsUser);
	}

	@Override
	public GsUser getUserById(String id) {
		logger.debug("根据id获取用户, id: {}", id);
		return gsUserMapper.selectByPrimaryKey(Long.parseLong(id));
	}

	@Override
	public int delUserById(String id) {
		logger.info("根据id删除用户, id: {}", id);
		return gsUserMapper.deleteByPrimaryKey(Long.parseLong(id));
	}

	@Override
	public Admin getAdminById(String id) {
		logger.info("根据id获取管理员账户, id: {}", id);
		return adminMapper.selectByPrimaryKey(Long.parseLong(id));
	}

	@Override
	public int modifyAdmin(Admin admin) {
		logger.info("修改管理员账户, id: {}", admin.getId());
		return adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		logger.info("获取所有管理员账户。");
		return adminMapper.selectAllAdmins();
	}

}
