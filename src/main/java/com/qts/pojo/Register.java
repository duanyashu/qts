package com.qts.pojo;

import java.io.Serializable;
import java.util.Date;

public class Register implements Serializable {
    /** ID*/
    private String id;

    /** 采样时间*/
    private Date createTime;

    /** 供应商*/
    private String supplier;

    /** 煤种*/
    private String coaltype;

    /** 车号*/
    private String carnum;

    /** 拓展字段*/
    private String factory;

    /** 卡号*/
    private String cardId;

    /** 货名*/
    private String cargoname;

    /** 关联编号*/
    private String linkid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getCoaltype() {
        return coaltype;
    }

    public void setCoaltype(String coaltype) {
        this.coaltype = coaltype == null ? null : coaltype.trim();
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum == null ? null : carnum.trim();
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getCargoname() {
        return cargoname;
    }

    public void setCargoname(String cargoname) {
        this.cargoname = cargoname == null ? null : cargoname.trim();
    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid == null ? null : linkid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", supplier=").append(supplier);
        sb.append(", coaltype=").append(coaltype);
        sb.append(", carnum=").append(carnum);
        sb.append(", factory=").append(factory);
        sb.append(", cardId=").append(cardId);
        sb.append(", cargoname=").append(cargoname);
        sb.append(", linkid=").append(linkid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}