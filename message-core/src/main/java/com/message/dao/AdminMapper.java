package com.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.message.domain.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin selectByAdminName(@Param("adminName") String adminName);
    
    List<Admin> selectAllAdmins();
}