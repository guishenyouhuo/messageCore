package com.message.service;

import java.util.List;

import com.message.domain.Admin;
import com.message.domain.GsUser;

public interface IUserService {
	
	List<GsUser> queryGsUsers(String type);
	
	GsUser getUserByNum(String userNum);
	
	/**
	 * 根据类型获取用户(除回收站)
	 * @param type 类型（可为空）
	 * @return 所有用户
	 */
	List<GsUser> getAllGsUsers(String type);
	
	GsUser getUserByName(String userName);
	
	Admin getAdminByName(String adminName);
	
	int addUser(GsUser gsUser);
	
	int modifyUser(GsUser gsUser);
	
	GsUser getUserById(String id);
	
	int delUserById(String id);
	
	Admin getAdminById(String id);
	
	int modifyAdmin(Admin admin);
	
	List<Admin> getAllAdmins();
}
