package com.message.dao;

import java.util.List;

import com.message.domain.MsgTemplate;

public interface MsgTemplateMapper {
    int deleteByPrimaryKey(String tmpKey);

    int insert(MsgTemplate record);

    int insertSelective(MsgTemplate record);

    MsgTemplate selectByPrimaryKey(String tmpKey);

    int updateByPrimaryKeySelective(MsgTemplate record);

    int updateByPrimaryKey(MsgTemplate record);
    
    List<MsgTemplate> selectAllTemplates();
}