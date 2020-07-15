package com.qts.dao;

import com.qts.pojo.RoleBranch;

public interface RoleBranchMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleBranch record);

    int insertSelective(RoleBranch record);

    RoleBranch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleBranch record);

    int updateByPrimaryKey(RoleBranch record);
}