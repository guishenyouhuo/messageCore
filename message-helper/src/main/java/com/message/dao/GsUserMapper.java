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
    
    List<GsUser> selectGsUsersByType(@Param("type")String type);
}