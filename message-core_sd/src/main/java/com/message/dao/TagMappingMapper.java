package com.message.dao;

import java.util.List;

import com.message.domain.TagMapping;

public interface TagMappingMapper {
    int deleteByPrimaryKey(String tagId);

    int insert(TagMapping record);

    int insertSelective(TagMapping record);

    TagMapping selectByPrimaryKey(String tagId);

    int updateByPrimaryKeySelective(TagMapping record);

    int updateByPrimaryKey(TagMapping record);
    
    List<TagMapping> selectAllTags();
}