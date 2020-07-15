package com.qts.pojo;

import java.io.Serializable;

public class AssayItem implements Serializable {
    /** id*/
    private String id;

    /** 字段名称*/
    private String itemName;

    /** 标准值*/
    private String standard;

    /** 类型（1.字符串，2数值，3日期）*/
    private Integer type;

    /** 模板id*/
    private String tempId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId == null ? null : tempId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemName=").append(itemName);
        sb.append(", standard=").append(standard);
        sb.append(", type=").append(type);
        sb.append(", tempId=").append(tempId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}