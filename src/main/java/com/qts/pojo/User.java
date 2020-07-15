package com.qts.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /** id*/
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String id;

    /** 账号*/
    @ExcelProperty(index = 0,value = "账号")
    private String username;

    /** 密码*/
    @ExcelIgnore
    private String password;

    /** 真实名称*/
    @ExcelProperty(index = 1,value = "用户名")
    private String realName;

    /** 是否锁定 0：正常，1：锁定*/
    @ExcelIgnore
    private Boolean locked;

    /** 创建时间*/
    @ExcelProperty(index = 2,value = "创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 创建人*/
    @ExcelIgnore
    private String createId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", realName=").append(realName);
        sb.append(", locked=").append(locked);
        sb.append(", createTime=").append(createTime);
        sb.append(", createId=").append(createId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}