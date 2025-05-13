package com.ftm.pojo;

import com.github.crab2died.annotation.ExcelField;

import java.math.BigDecimal;

public class TaxRefund {

    @ExcelField(title = "申报年月",order = 0)
    private String date;
    @ExcelField(title = "申报批次",order = 1)
    private String batch;
    @ExcelField(title = "退税额",order = 4)
    private String taxRefund;
    @ExcelField(title = "出口货物报关单号",order = 6)
    private String orderNo;
    @ExcelField(title = "出口日期",order = 8)
    private String outDate;
    @ExcelField(title = "出口商品代码",order = 9)
    private String code;
    @ExcelField(title = "出口商品名称",order = 10)
    private String name;
    @ExcelField(title = "出口数量",order = 9)
    private String quality;
    @ExcelField(title = "美元离岸价",order = 10)
    private String usdAmount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getTaxRefund() {
        return taxRefund;
    }

    public void setTaxRefund(String taxRefund) {
        this.taxRefund = taxRefund;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getUsdAmount() {
        return usdAmount;
    }

    public void setUsdAmount(String usdAmount) {
        this.usdAmount = usdAmount;
    }

    public void removeOrderNoLast3chars(){
        this.orderNo = this.orderNo.substring(0, this.orderNo.length()-3);
    }
}
