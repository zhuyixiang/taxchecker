package com.ftm.pojo;

import java.util.Objects;

public class OrderDetailKey {
    private String orderNo;
    private String productName;

    public OrderDetailKey(String orderNo, String productName) {
        this.orderNo = orderNo;
        this.productName = productName;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderDetailKey)) return false;
        OrderDetailKey that = (OrderDetailKey) o;
        return Objects.equals(getOrderNo(), that.getOrderNo()) && Objects.equals(getProductName(), that.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNo(), getProductName());
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
