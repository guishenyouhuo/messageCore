package com.message.dao;

import com.message.domain.Autoly;

public interface AutolyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Autoly record);

    int insertSelective(Autoly record);

    Autoly selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Autoly record);

    int updateByPrimaryKey(Autoly record);
}