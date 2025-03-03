package com.juanbai.example.common.module;

import java.io.Serializable;

/**
 * @author ALL
 * @date 2025/2/8
 * @Description
 */


public class Order {

    private long id;
    private long totalFee;
    private long paymentType;
    private long userId;
    private long status;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp payTime;
    private java.sql.Timestamp consignTime;
    private java.sql.Timestamp endTime;
    private java.sql.Timestamp closeTime;
    private java.sql.Timestamp commentTime;
    private java.sql.Timestamp updateTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }


    public long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(long paymentType) {
        this.paymentType = paymentType;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public java.sql.Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(java.sql.Timestamp payTime) {
        this.payTime = payTime;
    }


    public java.sql.Timestamp getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(java.sql.Timestamp consignTime) {
        this.consignTime = consignTime;
    }


    public java.sql.Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Timestamp endTime) {
        this.endTime = endTime;
    }


    public java.sql.Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(java.sql.Timestamp closeTime) {
        this.closeTime = closeTime;
    }


    public java.sql.Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(java.sql.Timestamp commentTime) {
        this.commentTime = commentTime;
    }


    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}

