package com.qts.service.impl;

import com.qts.dao.AssayDetailsMapper;
import com.qts.dao.AssayMapper;
import com.qts.pojo.Assay;
import com.qts.pojo.AssayDetails;
import com.qts.pojo.User;
import com.qts.request.AssayDetailsForm;
import com.qts.request.AssayForm;
import com.qts.response.LayuiData;
import com.qts.response.ReslutData;
import com.qts.service.IAssayService;
import com.qts.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 化验业务逻辑层
 * @author: duanyashu
 * @time: 2020-07-09 09:17
 */
@Service
public class AssayServiceImpl implements IAssayService {

    @Autowired
    AssayMapper assayMapper;
    @Autowired
    AssayDetailsMapper assayDetailsMapper;

    @Override
    public LayuiData listData(AssayForm assayForm) {
        List<Assay> assayList = assayMapper.selectAssayList(assayForm);
        int count = assayMapper.selectAssayListCount(assayForm);
        return new LayuiData(assayList,count);
    }

    @Override
    public ReslutData addDataSubmit(AssayDetailsForm assayDetailsForm) {
        List<String> itemNames = assayDetailsForm.getItemNames();
        for (int i = 0; i < itemNames.size(); i++) {
            String s =  itemNames.get(i);
            assayDetailsForm.setItemName(s);
            assayDetailsForm.setStandard(assayDetailsForm.getItemStandards().get(i));
            assayDetailsForm.setValue(assayDetailsForm.getItemValues().get(i));
            assayDetailsForm.setId(IdUtils.getId());
            assayDetailsMapper.insertSelective(assayDetailsForm);
        }
        Assay assay = new Assay();
        assay.setId(assayDetailsForm.getAssayId());
        assay.setState(1);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        assay.setAddId(user.getId());
        assay.setAddName(user.getRealName());
        assay.setAddTime(new Date());
        assayMapper.updateByPrimaryKeySelective(assay);
        return ReslutData.success();
    }

    @Override
    public Map<String, Object> getDetail(String id) {
        Map<String, Object> retMap = new HashMap<>();
        Assay assay = assayMapper.selectByPrimaryKey(id);
        retMap.put("assay",assay);
        List<AssayDetails> assayDetails = assayDetailsMapper.selectByAssayId(id);
        retMap.put("assayDetails",assayDetails);
        return retMap;
    }
}
