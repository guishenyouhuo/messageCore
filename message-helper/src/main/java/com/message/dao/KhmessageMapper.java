package com.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.message.domain.Khmessage;
import com.message.domain.KhmessageWithBLOBs;

public interface KhmessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KhmessageWithBLOBs record);

    int insertSelective(KhmessageWithBLOBs record);

    KhmessageWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KhmessageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(KhmessageWithBLOBs record);

    int updateByPrimaryKey(Khmessage record);
    
    List<String> selectMsgIdByUser(@Param("srcUser") String srcUser);
    
    int batchUpdateByUser(@Param("fpUser") String fpUser, @Param("msgList")List<String> msgList);
}