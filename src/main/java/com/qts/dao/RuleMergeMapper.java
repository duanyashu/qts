package com.qts.dao;

import com.qts.pojo.RuleMerge;
import org.apache.ibatis.annotations.Param;

public interface RuleMergeMapper {
    int deleteByPrimaryKey(String id);

    int insert(RuleMerge record);

    int insertSelective(RuleMerge record);

    RuleMerge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleMerge record);

    int updateByPrimaryKey(RuleMerge record);

    RuleMerge selectBySender(@Param("sender")String sender,@Param("type") String type);
}