package com.qts.request;

import com.qts.pojo.User;

/**
 * @description:
 * @author: duanyashu
 * @time: 2020-07-15 10:28
 */
public class UserForm  extends User {

    //当前页码
    private int currentPage = 1;

    //分页查询的索引  = （页码-1）*每页条数
    private int startRow;

    //没有显示的条数
    private int pageSize = 10;


    public int getStartRow() {
        //计算分页查询开始的索引
        this.startRow = (currentPage-1)*pageSize;
        return startRow;
    }



    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
