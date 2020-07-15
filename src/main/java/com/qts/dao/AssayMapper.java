package com.qts.dao;


import com.qts.pojo.Assay;
import com.qts.request.AssayForm;

import java.util.List;

public interface AssayMapper {
    int deleteByPrimaryKey(String id);

    int insert(Assay record);

    int insertSelective(Assay record);

    Assay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Assay record);

    int updateByPrimaryKeyWithBLOBs(Assay record);

    int updateByPrimaryKey(Assay record);

    List<Assay> selectAssayList(AssayForm assayForm);
    int selectAssayListCount(AssayForm assayForm);
}