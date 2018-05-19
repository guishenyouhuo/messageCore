package com.message.dao;

import java.util.List;
import java.util.Map;

import com.message.domain.LoginRecord;

public interface LoginRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    LoginRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);
    
    int selectLoginRecordCount(@SuppressWarnings("rawtypes") Map paramMap);
    
    List<LoginRecord> selectLoginRecordByPage(@SuppressWarnings("rawtypes") Map paramMap);
}