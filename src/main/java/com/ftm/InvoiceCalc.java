package com.ftm;

import com.ftm.biz.XmlParser;
import com.ftm.config.ConfigUtils;
import com.ftm.pojo.Order;
import com.ftm.pojo.OrderDetail;
import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class InvoiceCalc {

    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        ConfigUtils.init();

        XmlParser xmlParser = new XmlParser();
        List<Order> orders = xmlParser.getOrderList();

        StringBuffer output = new StringBuffer();

        for (Order order : orders) {
            if ("FOB".equalsIgnoreCase(order.getCjType())) {
                BigDecimal currencyRate = getCurrencyRate(order.getCurrency());
                order.setOffAmountCNY(order.getTotalAmount().multiply(currencyRate).setScale(4, RoundingMode.HALF_UP));
                order.setGoodsAmount(order.getTotalAmount());
                order.setGoodsAmountCNY(order.getGoodsAmount().multiply(currencyRate).setScale(4, RoundingMode.HALF_UP));
                order.setCurrencyRate(currencyRate);
                order.getOrderDetails().forEach(detail -> {
                    detail.setInvoiceAmountCNY(detail.getAmount().multiply(currencyRate).setScale(4, RoundingMode.HALF_UP));
                    detail.setInvoicePriceCNY(detail.getAmount().divide(detail.getUnit(), 12, RoundingMode.HALF_UP));
                });

                output.append(String.format("FOB离岸价格%s%s，汇率%s，折合人民币%s;", order.getCurrency(), order.getTotalAmount(), currencyRate, order.getOffAmountCNY()));
                output.append("\n");
                output.append("单位:千克； ");
                output.append("合同号：" + order.getOrderId());
                output.append("\n");

                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    output.append(String.format("商品 %s, 总价 %s, 单位 %s", orderDetail.getProductName(), orderDetail.getInvoiceAmountCNY(), orderDetail.getUnit()));
                    output.append("\n");
                }

            } else if ("CIF".equalsIgnoreCase(order.getCjType())) {
                BigDecimal allFeeCNY = order.getYfAmount().multiply(getCurrencyRate(order.getYfCurrency())).add(order.getBfAmount().multiply(getCurrencyRate(order.getBfCurrency()))).setScale(2, RoundingMode.HALF_UP);
                BigDecimal currencyRate = getCurrencyRate(order.getCurrency());
                order.setOffAmountCNY(order.getTotalAmount().multiply(currencyRate.setScale(4, RoundingMode.HALF_UP)));
                order.setCurrencyRate(currencyRate);
                order.setGoodsAmountCNY(order.getOffAmountCNY().subtract(allFeeCNY).setScale(4, RoundingMode.HALF_UP));
                order.setGoodsAmount(order.getGoodsAmountCNY().divide(currencyRate, 4, RoundingMode.HALF_UP));

                order.setOffAmountCNY(order.getOffAmountCNY().setScale(2, RoundingMode.HALF_UP));
                order.setGoodsAmount(order.getGoodsAmount().setScale(2, RoundingMode.HALF_UP));
                order.setGoodsAmountCNY(order.getGoodsAmountCNY().setScale(2, RoundingMode.HALF_UP));
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    BigDecimal ratio = orderDetail.getUnit().divide(order.getTotalUnit(), 4, RoundingMode.HALF_UP);
                    BigDecimal feeCny = allFeeCNY.multiply(ratio).setScale(4, RoundingMode.HALF_UP);
                    orderDetail.setInvoiceAmountCNY(orderDetail.getAmount().multiply(currencyRate).subtract(feeCny).setScale(4, RoundingMode.HALF_UP));
                    //orderDetail.setInvoicePriceCNY(orderDetail.getInvoiceAmountCNY().divide(orderDetail.getUnit(), 12, BigDecimal.ROUND_HALF_UP));
                    orderDetail.setInvoiceAmountCNY(orderDetail.getInvoiceAmountCNY().setScale(2, RoundingMode.HALF_UP));
                }

                output.append(String.format("CIF离岸价格%s%s，汇率%s，折合人民币%s;", order.getCurrency(), order.getTotalAmount(), currencyRate, order.getOffAmountCNY()));
                output.append("\n");
                output.append(String.format("其中:运费:%s%s: 保费:%s%s; 货款:%s%s，折合RMB%s；", order.getYfCurrency(), order.getYfAmount(), order.getBfCurrency(), order.getBfAmount(), order.getCurrency(), order.getGoodsAmount(), order.getGoodsAmountCNY()));
                output.append("\n");
                output.append("单位:千克； ");
                output.append("合同号：" + order.getOrderId());
                output.append("\n");

                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    output.append(String.format("商品 %s, 总价 %s, 单位 %s", orderDetail.getProductName(), orderDetail.getInvoiceAmountCNY(), orderDetail.getUnit()));
                    output.append("\n");
                }
            } else if ("C&F".equalsIgnoreCase(order.getCjType())) {
                BigDecimal allFeeCNY = order.getYfAmount().multiply(getCurrencyRate(order.getYfCurrency())).setScale(2, RoundingMode.HALF_UP);
                BigDecimal currencyRate = getCurrencyRate(order.getCurrency());
                order.setOffAmountCNY(order.getTotalAmount().multiply(currencyRate.setScale(4, RoundingMode.HALF_UP)));
                order.setCurrencyRate(currencyRate);
                order.setGoodsAmountCNY(order.getOffAmountCNY().subtract(allFeeCNY).setScale(4, RoundingMode.HALF_UP));
                order.setGoodsAmount(order.getGoodsAmountCNY().divide(currencyRate, 4, RoundingMode.HALF_UP));

                order.setOffAmountCNY(order.getOffAmountCNY().setScale(2, RoundingMode.HALF_UP));
                order.setGoodsAmount(order.getGoodsAmount().setScale(2, RoundingMode.HALF_UP));
                order.setGoodsAmountCNY(order.getGoodsAmountCNY().setScale(2, RoundingMode.HALF_UP));
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    BigDecimal ratio = orderDetail.getUnit().divide(order.getTotalUnit(), 4, RoundingMode.HALF_UP);
                    BigDecimal feeCny = allFeeCNY.multiply(ratio).setScale(4, RoundingMode.HALF_UP);
                    orderDetail.setInvoiceAmountCNY(orderDetail.getAmount().multiply(currencyRate).subtract(feeCny).setScale(4, RoundingMode.HALF_UP));
                    //orderDetail.setInvoicePriceCNY(orderDetail.getInvoiceAmountCNY().divide(orderDetail.getUnit(), 12, BigDecimal.ROUND_HALF_UP));
                    orderDetail.setInvoiceAmountCNY(orderDetail.getInvoiceAmountCNY().setScale(2, RoundingMode.HALF_UP));
                }

                output.append(String.format("C&F离岸价格%s%s，汇率%s，折合人民币%s;", order.getCurrency(), order.getTotalAmount(), currencyRate, order.getOffAmountCNY()));
                output.append("\n");
                output.append(String.format("其中:运费:%s%s; 货款:%s%s，折合RMB%s；", order.getYfCurrency(), order.getYfAmount(), order.getCurrency(), order.getGoodsAmount(), order.getGoodsAmountCNY()));
                output.append("\n");
                output.append("单位:千克； ");
                output.append("合同号：" + order.getOrderId());
                output.append("\n");

                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    output.append(String.format("商品 %s, 总价 %s, 单位 %s", orderDetail.getProductName(), orderDetail.getInvoiceAmountCNY(), orderDetail.getUnit()));
                    output.append("\n");
                }
            }else {
                System.err.printf("unknown CJtype: %s, %s", order.getOrderNo(), order.getCjType());
                System.out.println();
                System.out.println();
            }

            output.append("\n");
            output.append("\n");
        }

        System.out.println(output);
        System.out.println(orders.size());
    }

    private static BigDecimal getCurrencyRate(String currency) {
        if ("USD".equalsIgnoreCase(currency)) {
            return ConfigUtils.getUsdRate();
        } else if ("EUR".equalsIgnoreCase(currency)) {
            return ConfigUtils.getEurRate();
        } else if ("AUD".equalsIgnoreCase(currency)) {
            return ConfigUtils.getAudRate();
        } else if ("CNY".equalsIgnoreCase(currency)) {
            return BigDecimal.ONE;
        } else {
            System.err.printf("unknown currency: %s%n", currency);
            return null;
        }
    }
}
