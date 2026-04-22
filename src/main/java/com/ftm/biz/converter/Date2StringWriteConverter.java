package com.ftm.biz.converter;

import com.github.crab2died.converter.WriteConvertible;
import com.github.crab2died.utils.DateUtils;

import java.util.Date;

public class Date2StringWriteConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object) {
        Date date = (Date) object;
        return DateUtils.date2Str(date, DateUtils.DATE_FORMAT_DAY);
    }
}
