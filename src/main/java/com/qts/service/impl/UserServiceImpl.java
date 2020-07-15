package com.qts.service.impl;

import com.qts.dao.BranchMapper;
import com.qts.dao.MenuMapper;
import com.qts.entity.MenuVo;
import com.qts.pojo.Branch;
import com.qts.pojo.Menu;
import com.qts.pojo.User;
import com.qts.response.ReslutData;
import com.qts.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 用户管理服务层
 * @author: duanyashu
 * @time: 2020-07-02 15:48
 */
@Service
public class UserServiceImpl implements IUserService {

    public final  static String SESSION_LOGIN_KEY="sessionUser";

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    BranchMapper branchMapper;

    @Override
    public ReslutData login(String username, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
            //获取shiro中保存的用户信息
            User user = (User) subject.getPrincipal();
            //获取session
            Session session = subject.getSession();
            //放入登录的对象
            session.setAttribute(SESSION_LOGIN_KEY,user);
            //查询用户对应的厂区
            List<Branch> branchs = branchMapper.selectByUserId(user.getId());
            //将厂区信息放入session
            session.setAttribute("branchs",branchs);
            String url="/index";
            List<MenuVo> menuList = menuMapper.selectByUserId("0","menu",user.getId());
            /*for (Menu menu : menuList) {
                if ("caiyang".equals(menu.getMenuCode())){
                    url = "/sample/console";
                }
            }*/
            List<String> menuCodes = menuList.stream().map(Menu::getMenuCode).collect(Collectors.toList());
            if (menuCodes.contains("caiyang")){
                url = "/sample/console";
            }
            return ReslutData.success(url);
        }catch (UnknownAccountException e){
            return ReslutData.fail("账号错误");
        }catch (IncorrectCredentialsException e){
            return  ReslutData.fail("密码错误");
        }catch (LockedAccountException e){
            return  ReslutData.fail("账号被锁定，请联系管理员");
        }catch (AuthenticationException e){
            return ReslutData.fail("账号认证失败");
        }
    }

    @Override
    public List<MenuVo> getMenuTree() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return setMenuTree("0","menu",user.getId());
    }

    private List<MenuVo> setMenuTree(String parentId,String menuType,String userId){
        List<MenuVo> menuList = menuMapper.selectByUserId(parentId, menuType,userId);
        if (menuList.size()>0){
            for (MenuVo menuVo : menuList) {
                menuVo.setNextMenu(setMenuTree(menuVo.getId(),menuType,userId));
            }
        }else{
            menuList = menuMapper.selectByUserId(parentId,"button",userId);
        }
        return menuList;
    }
}
