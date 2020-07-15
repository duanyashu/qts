package com.qts.service;

import com.qts.request.SampleForm;
import com.qts.response.LayuiData;
import com.qts.response.ReslutData;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 采样服务层接口
 * @author: duanyashu
 * @time: 2020-07-03 15:45
 */
public interface ISampleService {

    public void data();

    public String shuaka(String id);
    public String saoma(String sampleCode);

    public LayuiData list(SampleForm sampleForm);
}
