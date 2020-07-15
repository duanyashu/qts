package com.qts.controller;

import com.qts.dao.RoleMapper;
import com.qts.dao.UserMapper;
import com.qts.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: duanyashu
 * @time: 2020-07-14 15:45
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    RoleMapper roleMapper;

    @RequestMapping("/userAddPage")
    public String userAddPage(Model model){
        List<Map<String, Object>> list = roleMapper.selectRoleMap();
        model.addAttribute("roleList",list);
        return "userAdd";
    }
}
