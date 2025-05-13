package com.ftm.biz.entity;

import java.util.Objects;

public class TaxKey {
    private String orderNo;
    private String productCode;
    private String productName;
    private String outDate;

    public TaxKey(String orderNo, String productCode) {
        this.setOrderNo(orderNo);
        this.setProductCode(productCode);
    }

    public TaxKey(String orderNo, String productCode, String productName, String outDate) {
        this.setOrderNo(orderNo);
        this.setProductCode(productCode);
        this.setProductName(productName);
        this.setOutDate(outDate);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TaxKey)) return false;
        TaxKey taxKey = (TaxKey) o;
        return Objects.equals(getOrderNo(), taxKey.getOrderNo()) && Objects.equals(getProductCode(), taxKey.getProductCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNo(), getProductCode());
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Override
    public String toString() {
        return "TaxKey{" +
                "orderNo='" + orderNo + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", outDate='" + outDate + '\'' +
                '}';
    }
}
