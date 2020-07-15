package com.qts.dao;

import com.qts.pojo.AssayItem;

import java.util.List;

public interface AssayItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssayItem record);

    int insertSelective(AssayItem record);

    AssayItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssayItem record);

    int updateByPrimaryKey(AssayItem record);

    List<AssayItem> selectByTempl(String templet);
}