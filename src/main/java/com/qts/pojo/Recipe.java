package com.qts.pojo;

import java.io.Serializable;
import java.util.Date;

public class Recipe implements Serializable {
    /** id*/
    private String id;

    /** 来源地*/
    private String sender;

    /** 煤种类别*/
    private String type;

    /** 占比*/
    private Float value;

    /** 厂区id*/
    private String branchCode;

    /** 配方时间*/
    private Date recipeTime;

    /** 创建时间*/
    private Date createTime;

    /** 创建人id*/
    private String createId;

    /** 创建人姓名*/
    private String createName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode == null ? null : branchCode.trim();
    }

    public Date getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(Date recipeTime) {
        this.recipeTime = recipeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sender=").append(sender);
        sb.append(", type=").append(type);
        sb.append(", value=").append(value);
        sb.append(", branchCode=").append(branchCode);
        sb.append(", recipeTime=").append(recipeTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createId=").append(createId);
        sb.append(", createName=").append(createName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}