package com.message.dao;

import java.util.List;
import java.util.Map;

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
    
    int selectMessageCount(@SuppressWarnings("rawtypes") Map paramMap);
    
    List<KhmessageWithBLOBs> selectMessageByPage(@SuppressWarnings("rawtypes") Map paramMap);
    
    List<KhmessageWithBLOBs> searchMessageByCondition(@SuppressWarnings("rawtypes") Map condition);
    
    List<String> queryLinkedTel(@Param("telNo") String telNo, @Param("gsNum") String gsNum);
    
    List<String> queryExistTel(@Param("telList") List<String> telList);
    
    int bactchInsert(@Param("msgList") List<KhmessageWithBLOBs> msgList);
    
    List<String> selectMsgIdByUser(@Param("srcUser") String srcUser);
    
    int batchUpdateByUser(@Param("fpUser") String fpUser, @Param("msgList")List<String> msgList);
    
    int updateToUnVisit();
}