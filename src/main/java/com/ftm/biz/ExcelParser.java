package com.ftm.biz;

import com.ftm.pojo.TaxRefund;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String,String> parseProducts(String fileName) throws Excel4JException, IOException {
        Map<String,String> nameCodeMap = new HashMap<String,String>();

        List<Product> products = ExcelUtils.getInstance().readExcel2Objects(fileName, Product.class, 1);

        for(Product product: products){
            nameCodeMap.put(product.getName().replace("红心", "").trim(), product.getCode());
        }
        return nameCodeMap;
    }
}
