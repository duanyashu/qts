package com.qts.controller;

import com.qts.pojo.User;
import com.qts.request.SampleForm;
import com.qts.response.LayuiData;
import com.qts.response.ReslutData;
import com.qts.service.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

/**
 * @description: 采样模块控制器
 * @author: duanyashu
 * @time: 2020-07-03 09:00
 */
@Controller
@RequestMapping("/sample")
public class SampleContorller {

    @Autowired
    ISampleService sampleService;

    @RequestMapping("/console")
    public String console(){
        return "sampleConsole";
    }

    @RequestMapping("/data")
    @ResponseBody
    public void data(){
        sampleService.data();
    }

    /**
     * 刷卡的方法
     * @param id  用户刷的卡号
     * @return
     */
    @RequestMapping("/shuaka/{cardId}")
    @ResponseBody
    public String shuaka(@PathVariable("cardId")  String id) {
        return  sampleService.shuaka(id);
    }

    @RequestMapping("/saoma/{sampleCode}")
    @ResponseBody
    public String saoma(@PathVariable String sampleCode){
        return  sampleService.saoma(sampleCode);
    }

    @RequestMapping("listPage")
    public String list(){
        return "sampleList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public LayuiData data(SampleForm sampleForm){
        return sampleService.list(sampleForm);
    }
}
