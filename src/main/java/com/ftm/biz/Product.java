package com.ftm.biz;

import com.github.crab2died.annotation.ExcelField;

public class Product {
    @ExcelField(title = "产品编号",order = 0)
    private String code;
    @ExcelField(title = "中文名称",order = 2)
    private String name;

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
}
