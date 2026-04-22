package com.ftm.pojo;


import com.ftm.biz.converter.String2BigDecimalReadConverter;
import com.github.crab2died.annotation.ExcelField;

import java.math.BigDecimal;

public class PurchaseDetail {
    @ExcelField(title = "采购合同号", order = 0)
    private String purchaseContractNo;
    @ExcelField(title = "销售合同号", order = 13)
    private String salesContractNo;
    @ExcelField(title = "产品编号", order = 1)
    private String productNo;
    @ExcelField(title = "中文名称", order = 4)
    private String chineseName;
    @ExcelField(title = "英文名称", order = 5)
    private String englishName;
    @ExcelField(title = "签约数量", order = 8, readConverter = String2BigDecimalReadConverter.class)
    private BigDecimal signNumber;
    @ExcelField(title = "单位", order = 7)
    private String unit;
    @ExcelField(title = "采购单价", order = 9, readConverter = String2BigDecimalReadConverter.class)
    private BigDecimal purchasePrice;
    @ExcelField(title = "采购金额", order = 10, readConverter = String2BigDecimalReadConverter.class)
    private BigDecimal purchaseAmount;

    private BigDecimal purchaseAmountCNY;



    private String buyer;

    public BigDecimal getPurchasePriceCNY() {
        return purchasePriceCNY;
    }

    public void setPurchasePriceCNY(BigDecimal purchasePriceCNY) {
        this.purchasePriceCNY = purchasePriceCNY;
    }

    private BigDecimal purchasePriceCNY;
    private String supplier;
    private String purchaseCurrency;
    private BigDecimal purchaseCurrencyRate;

    public String getSalesContractNo() {
        return salesContractNo;
    }

    public void setSalesContractNo(String salesContractNo) {
        this.salesContractNo = salesContractNo;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }


    public String getPurchaseContractNo() {
        return purchaseContractNo;
    }

    public void setPurchaseContractNo(String purchaseContractNo) {
        this.purchaseContractNo = purchaseContractNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public BigDecimal getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(BigDecimal signNumber) {
        this.signNumber = signNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public BigDecimal getPurchaseAmountCNY() {
        return purchaseAmountCNY;
    }

    public void setPurchaseAmountCNY(BigDecimal purchaseAmountCNY) {
        this.purchaseAmountCNY = purchaseAmountCNY;
    }

    public void calcPurchaseAmountCNY(BigDecimal rate) {
        this.purchaseAmountCNY = this.purchaseAmount.multiply(rate);
    }

    public BigDecimal getPurchaseCurrencyRate() {
        return purchaseCurrencyRate;
    }

    public void setPurchaseCurrencyRate(BigDecimal purchaseCurrencyRate) {
        this.purchaseCurrencyRate = purchaseCurrencyRate;
    }

    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    public void setPurchaseCurrency(String purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
