package com.qts.request;

import com.qts.pojo.Assay;
import com.qts.pojo.Sample;

/**
 * @description: 采样查询封装类
 * @author: duanyashu
 * @time: 2020-07-08 14:46
 */
public class AssayForm extends Assay {

    //查询用户添加起始时间
    private String startTime;

    //查询用户添加截止时间
    private String endTime;

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


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "UserForm [startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
