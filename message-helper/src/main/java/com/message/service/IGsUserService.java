package com.message.service;
import java.util.List;
import com.message.domain.GsUser;
public interface IGsUserService {

	/**
	 * 根据类型获取用户
	 * @param type 类型（可为空）
	 * @return 所有用户
	 */
	List<GsUser> getAllGsUsers(String type);
}
