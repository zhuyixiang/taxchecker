package com.ftm.biz;

import com.ftm.pojo.TaxRefund;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
    public List<TaxRefund> parseOrder(String path) throws Excel4JException, IOException {
        List<TaxRefund> refundList = new ArrayList<TaxRefund>();
        File file = new File(path);
        for(String fileName: file.list()){
            if(fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                List<TaxRefund> refunds = ExcelUtils.getInstance().readExcel2Objects(path + "/" + fileName, TaxRefund.class, 0);
                refundList.addAll(refunds);
            }
        }
        return refundList;
    }
}
