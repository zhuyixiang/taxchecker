package com.ftm.biz.converter;

import com.github.crab2died.converter.WriteConvertible;

import java.math.BigDecimal;

public class Bigcimal2StringWriteConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object) {
        BigDecimal value = (BigDecimal) object;
        if(value == null){
            return "";
        }else {
            return value.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
    }
}
