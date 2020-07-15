package com.qts.controller;

import com.qts.dao.AssayDetailsMapper;
import com.qts.dao.AssayItemMapper;
import com.qts.pojo.Assay;
import com.qts.pojo.AssayDetails;
import com.qts.pojo.AssayItem;
import com.qts.pojo.User;
import com.qts.request.AssayDetailsForm;
import com.qts.request.AssayForm;
import com.qts.response.LayuiData;
import com.qts.response.ReslutData;
import com.qts.service.IAssayService;
import com.qts.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 化验表控制器
 * @author: duanyashu
 * @time: 2020-07-09 09:07
 */
@Controller
@RequestMapping("/assay")
public class AssayController {

    @Autowired
    IAssayService assayService;
    @Autowired
    AssayItemMapper assayItemMapper;
    @Autowired
    AssayDetailsMapper assayDetailsMapper;

    @RequestMapping("/page")
    public String page(){
        return "assayList";
    }

    @RequestMapping("/optionPage")
    public String optionPage(){
        return "optionAssayList";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public LayuiData listData(AssayForm assayForm){
        return assayService.listData(assayForm);
    }

    @RequestMapping("/assayAddData")
    public String assayAddData(@ModelAttribute Assay assay, Model model){
        List<AssayItem> assayItems = assayItemMapper.selectByTempl(assay.getTemplateId());
        model.addAttribute("assayItems",assayItems);
        return "assayAddData";
    }

    @RequestMapping("/addDataSubmit")
    @ResponseBody
    public ReslutData addDataSubmit(AssayDetailsForm assayDetailsForm){
        return assayService.addDataSubmit(assayDetailsForm);
    }

    @RequestMapping("/detail")
    public String detail(String id,Model model){
        Map<String, Object> detail = assayService.getDetail(id);
        model.addAllAttributes(detail);
        return "assayDetail";
    }
}
