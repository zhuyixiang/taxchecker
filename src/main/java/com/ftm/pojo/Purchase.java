package com.ftm.pojo;


import com.ftm.biz.converter.String2BigDecimalReadConverter;
import com.ftm.biz.converter.String2DateReadConverter;
import com.ftm.config.ConfigUtils;
import com.github.crab2died.annotation.ExcelField;


import java.math.BigDecimal;
import java.util.Date;

public class Purchase {
    @ExcelField(title = "采购员", order = 1)
    private String purchasePerson;
    @ExcelField(title = "销售合同号", order = 2)
    private String salesContractNo;
    @ExcelField(title = "采购合同号", order = 3)
    private String purchaseContractNo;
    @ExcelField(title = "供应商", order = 8)
    private String supplier;
    @ExcelField(title = "签约公司", order = 10)
    private String signCompany;
    @ExcelField(title = "签约日期", order = 5, readConverter = String2DateReadConverter.class)
    private Date signDate;
    @ExcelField(title = "采购币种", order = 13)
    private String purchaseCurrency;
    @ExcelField(title = "合同金额", order = 14, readConverter = String2BigDecimalReadConverter.class)
    private BigDecimal purchaseAmount;
    @ExcelField(title = "人民币金额", order = 17, readConverter = String2BigDecimalReadConverter.class)
    private BigDecimal purchaseAmountCNY;

    private BigDecimal purchaseCurrencyRate;


    public String getPurchasePerson() {
        return purchasePerson;
    }

    public void setPurchasePerson(String purchasePerson) {
        this.purchasePerson = purchasePerson;
    }

    public String getSalesContractNo() {
        return salesContractNo;
    }

    public void setSalesContractNo(String salesContractNo) {
        this.salesContractNo = salesContractNo;
    }

    public String getPurchaseContractNo() {
        return purchaseContractNo;
    }

    public void setPurchaseContractNo(String purchaseContractNo) {
        this.purchaseContractNo = purchaseContractNo;
    }

    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    public void setPurchaseCurrency(String purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    public BigDecimal getPurchaseCurrencyRate() {
        return purchaseCurrencyRate;
    }

    public void setPurchaseCurrencyRate(BigDecimal purchaseCurrencyRate) {
        this.purchaseCurrencyRate = purchaseCurrencyRate;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
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

    public String getSignCompany() {
        return signCompany;
    }

    public void setSignCompany(String signCompany) {
        this.signCompany = signCompany;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void calcRate() {
        if("CNY".equals(purchaseCurrency)) {
            this.purchaseCurrencyRate = BigDecimal.ONE;
        }else if("USD".equals(purchaseCurrency)){
            this.purchaseCurrencyRate = ConfigUtils.getUsdRate();
        }else if("AUD".equals(purchaseCurrency)){
            this.purchaseCurrencyRate = ConfigUtils.getAudRate();
        }else if("EUR".equals(purchaseCurrency)){
            this.purchaseCurrencyRate = ConfigUtils.getEurRate();
        }else {
            this.purchaseCurrencyRate = this.purchaseAmountCNY.divide(this.purchaseAmountCNY, 4, BigDecimal.ROUND_HALF_UP);
        }
    }
}
