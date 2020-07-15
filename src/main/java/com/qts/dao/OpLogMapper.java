package com.qts.dao;

import com.qts.pojo.OpLog;

public interface OpLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(OpLog record);

    int insertSelective(OpLog record);

    OpLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OpLog record);

    int updateByPrimaryKey(OpLog record);
}