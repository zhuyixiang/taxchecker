package com.ftm.pojo;

import java.math.BigDecimal;

public class OrderDetail {
    private String orderNo;
    private String orderId;
    private String transportDate;
    private String xuhao;
    private String productCode;
    private String productName;
    private String currency;
    private BigDecimal amount;
    private BigDecimal unit;

    private BigDecimal invoicePriceCNY;
    private BigDecimal invoiceAmountCNY;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getXuhao() {
        return xuhao;
    }

    public void setXuhao(String xuhao) {
        this.xuhao = xuhao;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "OrderDetail{" +
                "报关='" + orderNo + '\'' +
                ", 订单号='" + orderId + '\'' +
                ", transportDate='" + transportDate + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public BigDecimal getInvoicePriceCNY() {
        return invoicePriceCNY;
    }

    public void setInvoicePriceCNY(BigDecimal invoicePriceCNY) {
        this.invoicePriceCNY = invoicePriceCNY;
    }

    public BigDecimal getInvoiceAmountCNY() {
        return invoiceAmountCNY;
    }

    public void setInvoiceAmountCNY(BigDecimal invoiceAmountCNY) {
        this.invoiceAmountCNY = invoiceAmountCNY;
    }
}
