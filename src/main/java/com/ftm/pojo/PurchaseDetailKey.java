package com.ftm.pojo;

import java.util.Objects;

public class PurchaseDetailKey {
    private String contractNo;
    private String productName;

    public PurchaseDetailKey(String contractNo, String productName) {
        this.setContractNo(contractNo);
        this.setProductName(productName);
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PurchaseDetailKey)) return false;
        PurchaseDetailKey that = (PurchaseDetailKey) o;
        return Objects.equals(getContractNo(), that.getContractNo()) && Objects.equals(getProductName(), that.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContractNo(), getProductName());
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
