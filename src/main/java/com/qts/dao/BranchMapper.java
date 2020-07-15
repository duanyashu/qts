package com.qts.dao;

import com.qts.pojo.Branch;

import java.util.List;

public interface BranchMapper {
    int deleteByPrimaryKey(String id);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);

    List<Branch> selectByUserId(String userId);
}