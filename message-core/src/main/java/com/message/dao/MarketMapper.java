package com.message.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.message.domain.Market;
import com.message.domain.MarketWithBLOBs;

public interface MarketMapper {
    int deleteByPrimaryKey(Short id);

    int insert(MarketWithBLOBs record);

    int insertSelective(MarketWithBLOBs record);

    MarketWithBLOBs selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(MarketWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MarketWithBLOBs record);

    int updateByPrimaryKey(Market record);
    
    int selectMarketCount(@SuppressWarnings("rawtypes") Map paramMap);
    
    List<MarketWithBLOBs> selectMarketByPage(@SuppressWarnings("rawtypes") Map paramMap);
    
    List<MarketWithBLOBs> searchMarketByCondition(@SuppressWarnings("rawtypes") Map condition);
    
    int queryExistTel(@Param("khTel") String khTel);
}