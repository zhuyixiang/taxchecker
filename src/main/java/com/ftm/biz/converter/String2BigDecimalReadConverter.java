package com.ftm.biz.converter;

import com.github.crab2died.converter.ReadConvertible;

import java.math.BigDecimal;

public class String2BigDecimalReadConverter implements ReadConvertible {
    @Override
    public Object execRead(String object) {
        if (object == null || object.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(Double.valueOf(object));
    }
}
