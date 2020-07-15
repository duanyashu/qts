package com.qts.dao;

import com.qts.pojo.AssayTemplate;

public interface AssayTemplateMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssayTemplate record);

    int insertSelective(AssayTemplate record);

    AssayTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssayTemplate record);

    int updateByPrimaryKeyWithBLOBs(AssayTemplate record);

    int updateByPrimaryKey(AssayTemplate record);
}