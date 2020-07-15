package com.qts.dao;

import com.qts.entity.SampleSaoMaVo;
import com.qts.pojo.Sample;
import com.qts.request.SampleForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SampleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sample record);

    int insertSelective(Sample record);

    Sample selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sample record);

    int updateByPrimaryKey(Sample record);

    List<Map<String,Object>> selectConsoleData(String branchCode);

    Sample selectByLinkId(String linkId);

    /**
     * 获取厂区的最大采样码
     * @param branchCode
     * @return
     */
    String selectMaxSampleCode(String branchCode);

    /**
     * 查询厂区对应的最新合样
     * @param branchCode
     * @return
     */
    Map<String,Object> selectLastMergeCodeAndCount(@Param("branchCode") String branchCode,@Param("ruleMergeId") String ruleMergeId);

    int updateMergeStateByMergeCode(@Param("mergeCode") String mergeCode,@Param("mergeState")Boolean mergeState,@Param(("state")) Boolean state);

    SampleSaoMaVo selectSaomaData(String sampleCode);

    List<Sample> selectSampleList(SampleForm sampleForm);

    int selectSampleListCount(SampleForm sampleForm);

}