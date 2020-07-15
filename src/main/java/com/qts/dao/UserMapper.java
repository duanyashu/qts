package com.qts.dao;

import com.qts.pojo.User;
import com.qts.request.UserForm;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    List<User> selectAll(UserForm userForm);
}