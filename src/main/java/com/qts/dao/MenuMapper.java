package com.qts.dao;

import com.qts.entity.MenuVo;
import com.qts.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<MenuVo> selectByUserId(@Param("parentId")String parentId, @Param("menuType")String menuType , @Param("userId") String userId);
}