package com.qts.service;

import com.qts.entity.MenuVo;
import com.qts.response.ReslutData;

import java.util.List;

/**
 * @description: 用户管理服务层接口
 * @author: duanyashu
 * @time: 2020-07-02 15:47
 */
public interface IUserService {

  ReslutData login(String username,String password);

  List<MenuVo> getMenuTree();
}
