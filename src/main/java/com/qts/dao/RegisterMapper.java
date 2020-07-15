package com.qts.dao;

import com.qts.pojo.Register;

public interface RegisterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Register record);

    int insertSelective(Register record);

    Register selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Register record);

    int updateByPrimaryKey(Register record);
}