package com.message.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.message.dao.LoginRecordMapper;
import com.message.domain.LoginRecord;
import com.message.service.ILoginRecordService;

@Service
public class LoginRecordServiceImpl implements ILoginRecordService {

	@Resource
	private LoginRecordMapper loginRecordMapper;
	@Override
	public int inserLoginRecord(LoginRecord record) {
		return loginRecordMapper.insertSelective(record);
	}
	@Override
	public int getLoginRecourCount(Map<String, Object> conditions) {
		return loginRecordMapper.selectLoginRecordCount(conditions);
	}
	@Override
	public List<LoginRecord> getLiginRecordByPage(int startNo, int pageSize,
			Map<String, Object> conditions) {
		conditions.put("startNo", startNo);
		conditions.put("pageSize", pageSize);
		return loginRecordMapper.selectLoginRecordByPage(conditions);
	}

}
