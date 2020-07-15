package com.qts.dao;

import com.qts.pojo.Recipe;

public interface RecipeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Recipe record);

    int insertSelective(Recipe record);

    Recipe selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Recipe record);

    int updateByPrimaryKey(Recipe record);
}