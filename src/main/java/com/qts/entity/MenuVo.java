package com.qts.entity;

import com.qts.pojo.Menu;

import java.util.List;

/**
 * @description: 菜单包装类
 * @author: duanyashu
 * @time: 2020-07-03 10:45
 */
public class MenuVo extends Menu {

    /**
     * 添加下一级菜单
     */
    private List<MenuVo> nextMenu;

    public List<MenuVo> getNextMenu() {
        return nextMenu;
    }

    public void setNextMenu(List<MenuVo> nextMenu) {
        this.nextMenu = nextMenu;
    }
}
