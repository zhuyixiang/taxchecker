package com.ftm;

import com.ftm.biz.ExcelParser;
import com.ftm.biz.XmlParser;
import com.ftm.biz.entity.TaxKey;
import com.ftm.pojo.OrderDetail;
import com.ftm.pojo.TaxRefund;
import com.github.crab2died.exceptions.Excel4JException;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Excel4JException, IOException, DocumentException {
        ExcelParser excelParser = new ExcelParser();
        List<TaxRefund> taxRefunds = excelParser.parseOrder("data/excel");
        taxRefunds.forEach(taxRefund -> taxRefund.removeOrderNoLast3chars());

        XmlParser xmlParser = new XmlParser();
        List<OrderDetail> orderList = xmlParser.getOrderList();

        Map<TaxKey, TaxRefund> taxRefundMap = new HashMap<>();

        for (TaxRefund taxRefund : taxRefunds) {
            TaxKey taxKey = new TaxKey(taxRefund.getOrderNo(), taxRefund.getCode(), taxRefund.getName(), taxRefund.getOutDate());
            taxRefundMap.put(taxKey, taxRefund);
        }

        List<OrderDetail> unRefundList = new ArrayList<>();

        for(OrderDetail orderDetail : orderList) {
            TaxKey taxKey = new TaxKey(orderDetail.getOrderNo(), orderDetail.getProductCode(), orderDetail.getProductName(), orderDetail.getTransportDate());
            if(!taxRefundMap.containsKey(taxKey)) {
                System.out.println(orderDetail.toString());
            }else{
                //System.out.println("fixed!");
            }
        }

        System.out.println("End!");
    }
}
