package com.ftm.biz.converter;

import com.github.crab2died.converter.ReadConvertible;
import com.github.crab2died.utils.DateUtils;

public class String2DateReadConverter implements ReadConvertible {
    @Override
    public Object execRead(String object) {
        return DateUtils.str2Date(object, DateUtils.DATE_FORMAT_DAY);
    }
}
