package com.ftm.pojo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String fileName;
    private String orderNo;
    private String orderId;
    private String transportDate;
    private String cjType;
    private String yfCurrency;
    private BigDecimal yfAmount;
    private String bfCurrency;
    private BigDecimal bfAmount;

    private BigDecimal totalAmount;
    private BigDecimal totalUnit;
    private String currency;
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    private BigDecimal offAmountCNY;
    private BigDecimal goodsAmount;
    private BigDecimal goodsAmountCNY;
    private BigDecimal currencyRate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransportDate() {
        return transportDate;
    }

    public void setTransportDate(String transportDate) {
        this.transportDate = transportDate;
    }

    public String getYfCurrency() {
        return yfCurrency;
    }

    public void setYfCurrency(String yfCurrency) {
        this.yfCurrency = yfCurrency;
    }

    public BigDecimal getYfAmount() {
        return yfAmount;
    }

    public void setYfAmount(BigDecimal yfAmount) {
        this.yfAmount = yfAmount;
    }

    public String getBfCurrency() {
        return bfCurrency;
    }

    public void setBfCurrency(String bfCurrency) {
        this.bfCurrency = bfCurrency;
    }

    public BigDecimal getBfAmount() {
        return bfAmount;
    }

    public void setBfAmount(BigDecimal bfAmount) {
        this.bfAmount = bfAmount;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCjType() {
        return cjType;
    }

    public void setCjType(String cjType) {
        this.cjType = cjType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(BigDecimal totalUnit) {
        this.totalUnit = totalUnit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getOffAmountCNY() {
        return offAmountCNY;
    }

    public void setOffAmountCNY(BigDecimal offAmountCNY) {
        this.offAmountCNY = offAmountCNY;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getGoodsAmountCNY() {
        return goodsAmountCNY;
    }

    public void setGoodsAmountCNY(BigDecimal goodsAmountCNY) {
        this.goodsAmountCNY = goodsAmountCNY;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }
}
