package com.ftm;

import com.ftm.biz.ExcelParser;
import com.ftm.biz.XmlParser;
import com.ftm.biz.entity.TaxKey;
import com.ftm.pojo.OrderDetail;
import com.ftm.pojo.TaxRefund;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Excel4JException, IOException, DocumentException {
        ExcelParser excelParser = new ExcelParser();
        List<TaxRefund> taxRefunds = excelParser.parseOrder("data/excel");
        taxRefunds.forEach(taxRefund -> taxRefund.removeOrderNoLast3chars());

        XmlParser xmlParser = new XmlParser();
        List<OrderDetail> orderList = xmlParser.getOrderDetailList();

        Map<TaxKey, TaxRefund> taxRefundMap = new HashMap<>();

        for (TaxRefund taxRefund : taxRefunds) {
            TaxKey taxKey = new TaxKey(taxRefund.getOrderNo(), taxRefund.getCode(), taxRefund.getName(), taxRefund.getOutDate());
            taxRefundMap.put(taxKey, taxRefund);
        }

        Map<String, String> productsMap = excelParser.parseProducts("data/products.xlsx");

        List<OrderDetail> taxRefundOrders = new ArrayList<>();
        for (OrderDetail orderDetail : orderList) {
            TaxKey taxKey = new TaxKey(orderDetail.getOrderNo(), orderDetail.getProductCode(), orderDetail.getProductName(), orderDetail.getTransportDate());
            TaxRefund foundTaxRefund = taxRefundMap.get(taxKey);
            if (foundTaxRefund == null) {
                System.out.println(orderDetail);
            } else {
                orderDetail.setTaxRefund(new BigDecimal(foundTaxRefund.getTaxRefund().replace(",","")));
                String ftCode = productsMap.get(orderDetail.getProductName());
                orderDetail.setFtProductCode(ftCode);
                taxRefundOrders.add(orderDetail);
            }
        }

        ExcelUtils.getInstance().exportObjects2Excel(taxRefundOrders, OrderDetail.class, true, null, true,  "data/taxRefund.xlsx");

        System.out.println("End!");
    }
}
