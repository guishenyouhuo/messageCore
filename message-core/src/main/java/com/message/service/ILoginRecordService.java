package com.message.service;

import java.util.List;
import java.util.Map;

import com.message.domain.LoginRecord;

public interface ILoginRecordService {

	int inserLoginRecord(LoginRecord record);
	
	int getLoginRecourCount(Map<String, Object> conditions);
	
	List<LoginRecord> getLiginRecordByPage(int startNo, int pageSize, Map<String, Object> conditions);
}
