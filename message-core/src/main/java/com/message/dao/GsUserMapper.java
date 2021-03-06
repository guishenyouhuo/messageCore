package com.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.message.domain.GsUser;

public interface GsUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsUser record);

    int insertSelective(GsUser record);

    GsUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsUser record);

    int updateByPrimaryKey(GsUser record);
    
    GsUser selectByUserName(@Param("userName") String userName);
    
    List<GsUser> selectGsUsers(@Param("userType") String userType);
    
    GsUser selectGsUserByNum(@Param("userNum") String userNum);
    
    List<GsUser> selectGsUsersByType(@Param("type")String type);
}