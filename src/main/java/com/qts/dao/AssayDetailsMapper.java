package com.qts.dao;

import com.qts.pojo.AssayDetails;

import java.util.List;

public interface AssayDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssayDetails record);

    int insertSelective(AssayDetails record);

    AssayDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssayDetails record);

    int updateByPrimaryKey(AssayDetails record);

    List<AssayDetails> selectByAssayId(String assayId);
}