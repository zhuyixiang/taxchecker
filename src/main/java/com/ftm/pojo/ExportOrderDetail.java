package com.ftm.pojo;

import com.github.crab2died.annotation.ExcelField;

import java.math.BigDecimal;

public class ExportOrderDetail {
    @ExcelField(title = "报关单号", order = 0)
    private String exportId;
    @ExcelField(title = "订单号", order = 1)
    private String orderNo;
    @ExcelField(title = "出口日期", order = 2)
    private String exportDate;
    @ExcelField(title = "产品名称", order = 3)
    private String productName;
    @ExcelField(title = "数量", order = 4)
    private BigDecimal quantity;
    @ExcelField(title = "订单人民币金额", order = 5)
    private BigDecimal amountCNY;
    @ExcelField(title = "购买价格", order = 6)
    private BigDecimal buyPrice;
    @ExcelField(title = "采购总金额", order = 7)
    private BigDecimal buyAmount;
    @ExcelField(title = "退税", order = 8)
    private BigDecimal taxRefund;
    @ExcelField(title = "退税后成本", order = 9)
    private BigDecimal afterTaxAmount;

    public String getExportId() {
        return exportId;
    }

    public void setExportId(String exportId) {
        this.exportId = exportId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmountCNY() {
        return amountCNY;
    }

    public void setAmountCNY(BigDecimal amountCNY) {
        this.amountCNY = amountCNY;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getTaxRefund() {
        return taxRefund;
    }

    public void setTaxRefund(BigDecimal taxRefund) {
        this.taxRefund = taxRefund;
    }

    public BigDecimal getAfterTaxAmount() {
        return afterTaxAmount;
    }

    public void setAfterTaxAmount(BigDecimal afterTaxAmount) {
        this.afterTaxAmount = afterTaxAmount;
    }
}
