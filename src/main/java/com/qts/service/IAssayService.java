package com.qts.service;

import com.qts.request.AssayDetailsForm;
import com.qts.request.AssayForm;
import com.qts.response.LayuiData;
import com.qts.response.ReslutData;

import java.util.Map;

/**
 * @description: 化验服务层接口
 * @author: duanyashu
 * @time: 2020-07-09 09:16
 */
public interface IAssayService {

    public LayuiData listData(AssayForm assayForm);

    public ReslutData addDataSubmit(AssayDetailsForm assayDetailsForm);

    public Map<String ,Object> getDetail(String id);
}
