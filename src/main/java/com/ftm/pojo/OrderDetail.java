package com.ftm.pojo;

import com.ftm.biz.Bigcimal2StringWriteConverter;
import com.github.crab2died.annotation.ExcelField;

import java.math.BigDecimal;

public class OrderDetail {
    @ExcelField(title = "报关单号", order = 6)
    private String orderNo;
    @ExcelField(title = "订单编号", order = 0)
    private String orderId;
    @ExcelField(title = "出口日期", order = 5)
    private String transportDate;
    private String xuhao;
    private String productCode;
    @ExcelField(title = "产品名称", order = 3)
    private String productName;
    private String currency;
    private BigDecimal amount;
    private BigDecimal unit;
    @ExcelField(title = "退税", order = 1, writeConverter = Bigcimal2StringWriteConverter.class)
    private BigDecimal taxRefund;
    private BigDecimal invoicePriceCNY;
    private BigDecimal invoiceAmountCNY;
    @ExcelField(title = "产品编码", order = 2)
    private String ftProductCode;
    @ExcelField(title = "退税率", order = 4, writeConverter = Bigcimal2StringWriteConverter.class)
    private BigDecimal taxRefundRate = new BigDecimal("0.115");

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

    public String toTaxRefundString() {
        return "OrderDetail{" +
                "报关='" + orderNo + '\'' +
                ", 订单号='" + orderId + '\'' +
                ", transportDate='" + transportDate + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", taxRefund='" + taxRefund + '\'' +
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

    public BigDecimal getTaxRefund() {
        return taxRefund;
    }

    public void setTaxRefund(BigDecimal taxRefund) {
        this.taxRefund = taxRefund;
    }

    public String getFtProductCode() {
        return ftProductCode;
    }

    public void setFtProductCode(String ftProductCode) {
        if (ftProductCode == null) {
            ftProductCode = "";
        }
        this.ftProductCode = ftProductCode;

    }

    public BigDecimal getTaxRefundRate() {
        return taxRefundRate;
    }

    public void setTaxRefundRate(BigDecimal taxRefundRate) {
        this.taxRefundRate = taxRefundRate;
    }
}
