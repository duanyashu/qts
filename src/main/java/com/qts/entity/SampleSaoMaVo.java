package com.qts.entity;

/**
 * @description: 扫码添加化验数据封装对象
 * @author: duanyashu
 * @time: 2020-07-07 16:56
 */
public class SampleSaoMaVo {

    private String id;
    private String margeCode;
    private String sender;
    private String type;
    private String ruleMergeId;
    private String branchCode;
    private String sampleCodes;
    private String margeCarnums;

    private Boolean state;

    //是否合样
    private Boolean mergeState;

    //合样规则数
    private int mergeRule;

    //已扫码的数量
    private int stateCount;


    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMergeRule() {
        return mergeRule;
    }

    public void setMergeRule(int mergeRule) {
        this.mergeRule = mergeRule;
    }

    public int getStateCount() {
        return stateCount;
    }

    public void setStateCount(int stateCount) {
        this.stateCount = stateCount;
    }

    public Boolean getMergeState() {
        return mergeState;
    }

    public void setMergeState(Boolean mergeState) {
        this.mergeState = mergeState;
    }

    public String getMargeCode() {
        return margeCode;
    }

    public void setMargeCode(String margeCode) {
        this.margeCode = margeCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRuleMergeId() {
        return ruleMergeId;
    }

    public void setRuleMergeId(String ruleMergeId) {
        this.ruleMergeId = ruleMergeId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getSampleCodes() {
        return sampleCodes;
    }

    public void setSampleCodes(String sampleCodes) {
        this.sampleCodes = sampleCodes;
    }

    public String getMargeCarnums() {
        return margeCarnums;
    }

    public void setMargeCarnums(String margeCarnums) {
        this.margeCarnums = margeCarnums;
    }
}
