package com.ftm;

import com.ftm.biz.ExcelParser;
import com.ftm.biz.XmlParser;
import com.ftm.pojo.*;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;
import org.apache.poi.util.StringUtil;
import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class FTMFinanceCalc {
    private static final Map<PurchaseDetailKey, PurchaseDetail> purchaseDetailMap = new HashMap<>();
    private static final Map<String, List<String>> productNameMap = new HashMap<>();
    private static final List<String> notTaxRefundList = new ArrayList<>();
    static Map<String, BigDecimal> rateMap = new HashMap<>();

    static {
        initRateMap();
        initProductNameMap();
        initNoTaxRefundList();
    }

    private static void initNoTaxRefundList() {
        notTaxRefundList.add("磷酸三镁");
        notTaxRefundList.add("羟基磷灰石");
        notTaxRefundList.add("羟基磷酸钙");


    }

    private static void initProductNameMap() {
        //L-瓜氨酸 DL-苹果酸 2:1

        addToNameMap("火龙果粉", "火龙果粉 红心");
        addToNameMap("L-瓜氨酸 DL-苹果酸", "L-瓜氨酸 DL-苹果酸 2:1");
        addToNameMap("石杉碱甲1%", "石杉碱甲A 1%");
        addToNameMap("石杉碱甲 1%", "石杉碱甲1%");
        addToNameMap("彩盒", "孕妇钙条彩盒", "钙锌条饮彩盒", "叶黄素咀嚼软胶囊彩盒", "钙镁锌氨基酸饮彩盒", "大童钙饮彩盒");

        addToNameMap("孕妇钙条", "产妇钙条");
        addToNameMap("甘氨酸", "L-甘氨酸");
        addToNameMap("乙酰左旋肉碱盐酸盐", "N-乙酰左旋肉碱盐酸盐");
        addToNameMap("钙锌+D3 15ml饮品", "钙锌D3饮");
        addToNameMap("水飞蓟提取物", "水飞蓟素");
        addToNameMap("磷酸三镁", "磷酸三镁（无水）", "磷酸三镁（五水）");
        addToNameMap("L-天门冬氨酸镁", "天冬氨酸镁");
        addToNameMap("复合矿物质-Min", "亚硒酸钠");
        addToNameMap("孕妇钙饮", "产妇钙条");
        addToNameMap("胶原蛋白肽", "牛胶原蛋白肽");
        addToNameMap("一水肌酸", "一水肌酸 200目");
        addToNameMap("Omega 369 软胶囊", "Omega 3,6,9软胶囊");
        addToNameMap("甘油磷酰胆碱", "α-甘油磷脂酰胆碱");
        addToNameMap("L-茶氨酸", "L-茶氨酸（四川同晟）");
        addToNameMap("白藜芦醇", "白藜芦醇 99%");
        addToNameMap("天门冬氨酸镁", "天冬氨酸镁");
        addToNameMap("VE 软胶囊", "天然维生素E软胶囊");
        addToNameMap("肌醇", "肌醇颗粒");
        addToNameMap("姜黄素", "姜黄");
        addToNameMap("枳实提取物", "苦橙提取物（50% 辛弗林）");
        addToNameMap("明胶", "鱼明胶");
        addToNameMap("L-精氨酸-α-酮戊二酸盐", "L-精氨酸 α-酮戊二酸盐 2:1");


        addToNameMap("维生素 D3 & VK2 软胶囊", "维生素 D3&VK2 软胶囊");
        addToNameMap("藤黄果提取物", "藤黄果 50%");
        addToNameMap("大童饮", "大童钙饮");
        addToNameMap("维生素D3软胶囊", "维生素D3 软胶囊 400iu");
        addToNameMap("支链氨基酸", "速溶支链氨基酸 2:1:1");
        addToNameMap("二甲氨基乙醇酒石酸氢盐", "二甲胺基乙醇酒石酸氢盐");
        addToNameMap("复合鱼油软胶囊", "鱼油Q10VE包衣软胶囊");
        addToNameMap("还原型谷胱甘肽", "谷胱甘肽");
        addToNameMap("绿茶提取物", "绿茶提取物 45%");
        addToNameMap("人参提取物", "红景天提取物", "红景天提取物（根）");
        addToNameMap("D-甘露糖", "甘露糖醇");
        addToNameMap("喜来芝提取物", "喜来芝提取物50%富里酸");
        addToNameMap("葡萄柚籽提取物", "葡萄柚提取物");
        addToNameMap("槐米提取物", "槐米提取物95%无水槲皮素");
        addToNameMap("钙镁锌氨基酸饮", "钙锌+D3 15ml饮品");
        addToNameMap("叶黄素软胶囊", "叶黄素咀嚼软胶囊");
        addToNameMap("甘油脂磷酰胆碱", "甘油磷脂酰胆碱");
        addToNameMap("石榴粉", "有机石榴粉");
        addToNameMap("羟基磷灰石", "羟基磷酸钙");
    }

    public static void addToNameMap(String name, String... otherNames) {
        if (productNameMap.containsKey(name)) {
            System.out.println(name + ", already in product name maps");
        }
        productNameMap.put(name, Arrays.asList(otherNames));
    }


    public static void main(String[] args) throws DocumentException, IOException, Excel4JException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        fillOrderDetailList(orderDetails);

        ExcelParser excelParser = new ExcelParser();
        List<PurchaseDetail> purchaseDetails = excelParser.parsePurchase("data/buy.xlsx");
        initPurchaseDetailMap(purchaseDetails);

        List<ExportOrderDetail> exportOrderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            ExportOrderDetail exportOrderDetail = new ExportOrderDetail();
            exportOrderDetail.setExportId(orderDetail.getOrderNo());
            exportOrderDetail.setOrderNo(orderDetail.getOrderId());
            exportOrderDetail.setExportDate(orderDetail.getTransportDate());
            exportOrderDetail.setProductName(orderDetail.getProductName());
            exportOrderDetail.setQuantity(orderDetail.getUnit());
            exportOrderDetail.setAmountCNY(orderDetail.getInvoiceAmountCNY());
            changeToFullOrderName(exportOrderDetail);
            fillPurchaseDetailInfo(exportOrderDetail);

            exportOrderDetails.add(exportOrderDetail);
        }
        ExcelUtils.getInstance().exportObjects2Excel(exportOrderDetails, ExportOrderDetail.class, true, null, true, "data/export_orders.xlsx");

    }

    private static void changeToFullOrderName(ExportOrderDetail exportOrderDetail) {
        if (exportOrderDetail.getOrderNo().equals("ATS24007-1&ATS24008&ATS24013&ATS")) {
            exportOrderDetail.setOrderNo("ATS24007-1&ATS24008&ATS24013&ATS24014&ATS24012");
        }
        if (exportOrderDetail.getOrderNo().equals("SMK25001")) {
            exportOrderDetail.setOrderNo("SMK25001-2");
        }
    }

    private static void fillPurchaseDetailInfo(ExportOrderDetail exportOrderDetail) {
        for (String orderNo : exportOrderDetail.getOrderNo().split("&")) {
            if (fillDetailInfo2(exportOrderDetail, orderNo)) {
                return;
            }
        }
        for (String orderNo : exportOrderDetail.getOrderNo().split(" ")) {
            if (fillDetailInfo2(exportOrderDetail, orderNo)) {
                return;
            }
        }
    }

    private static boolean fillDetailInfo2(ExportOrderDetail exportOrderDetail, String orderNo) {
        orderNo = orderNo.replace("#", "").trim();
        if (fillDetailInfo3(exportOrderDetail, orderNo)) return true;
        if (orderNo.contains("-")) {
            orderNo = orderNo.substring(0, orderNo.indexOf("-"));
            return fillDetailInfo3(exportOrderDetail, orderNo);
        }
        return false;
    }

    private static boolean fillDetailInfo3(ExportOrderDetail exportOrderDetail, String orderNo) {
        PurchaseDetailKey purchaseDetailKey = new PurchaseDetailKey(orderNo, exportOrderDetail.getProductName());
        PurchaseDetail purchaseDetail = purchaseDetailMap.get(purchaseDetailKey);
        if (purchaseDetail != null) {
            exportOrderDetail.setBuyPrice(purchaseDetail.getPurchasePriceCNY());
            exportOrderDetail.setBuyAmount(purchaseDetail.getPurchaseAmountCNY());

            exportOrderDetail.setTaxRefund(purchaseDetail.getPurchaseAmountCNY().multiply(new BigDecimal("0.115")).setScale(4, RoundingMode.HALF_UP));
            exportOrderDetail.setAfterTaxAmount(purchaseDetail.getPurchaseAmountCNY().multiply(new BigDecimal("0.885")).setScale(4, RoundingMode.HALF_UP));
            if (exportOrderDetail.getQuantity().doubleValue() != purchaseDetail.getSignNumber().doubleValue()) {
                if (!(exportOrderDetail.getProductName().contains("胶囊")
                        || exportOrderDetail.getProductName().contains("钙锌")
                        || exportOrderDetail.getProductName().contains("钙镁")
                        || exportOrderDetail.getProductName().contains("彩盒")
                        || exportOrderDetail.getProductName().contains("孕妇")
                        || exportOrderDetail.getProductName().contains("软糖")
                        || exportOrderDetail.getProductName().contains("大童饮"))) {
                    System.out.printf("quantity not equal,order no: %s, name: %s, export quantity: %s, buy no: %s %n", exportOrderDetail.getOrderNo(), exportOrderDetail.getProductName(), exportOrderDetail.getQuantity(), purchaseDetail.getSignNumber());
                    exportOrderDetail.setBuyAmount(purchaseDetail.getPurchasePriceCNY().multiply(exportOrderDetail.getQuantity()));
                    exportOrderDetail.setTaxRefund(exportOrderDetail.getBuyAmount().multiply(new BigDecimal("0.115")).setScale(4, RoundingMode.HALF_UP));
                    exportOrderDetail.setAfterTaxAmount(exportOrderDetail.getBuyAmount().multiply(new BigDecimal("0.885")).setScale(4, RoundingMode.HALF_UP));
                }
            }
            if (isNotTaxRefund(exportOrderDetail)) {
                exportOrderDetail.setTaxRefund(BigDecimal.ZERO);
                exportOrderDetail.setAfterTaxAmount(purchaseDetail.getPurchaseAmountCNY());
            }
            return true;
        } else {
            if (productNameMap.get(exportOrderDetail.getProductName()) != null) {
                for (String otherName : productNameMap.get(exportOrderDetail.getProductName())) {
                    purchaseDetailKey = new PurchaseDetailKey(orderNo, otherName);
                    purchaseDetail = purchaseDetailMap.get(purchaseDetailKey);
                    if (purchaseDetail != null) {
                        exportOrderDetail.setBuyPrice(purchaseDetail.getPurchasePriceCNY());
                        exportOrderDetail.setBuyAmount(purchaseDetail.getPurchaseAmountCNY());
                        exportOrderDetail.setTaxRefund(purchaseDetail.getPurchaseAmountCNY().multiply(new BigDecimal("0.115")).setScale(4, RoundingMode.HALF_UP));
                        exportOrderDetail.setAfterTaxAmount(purchaseDetail.getPurchaseAmountCNY().multiply(new BigDecimal("0.885")).setScale(4, RoundingMode.HALF_UP));
                        if (exportOrderDetail.getQuantity().doubleValue() != purchaseDetail.getSignNumber().doubleValue()) {
                            if (!(exportOrderDetail.getProductName().contains("胶囊")
                                    || exportOrderDetail.getProductName().contains("钙锌")
                                    || exportOrderDetail.getProductName().contains("钙镁")
                                    || exportOrderDetail.getProductName().contains("彩盒")
                                    || exportOrderDetail.getProductName().contains("孕妇")
                                    || exportOrderDetail.getProductName().contains("软糖")
                                    || exportOrderDetail.getProductName().contains("大童饮"))) {
                                System.out.printf("quantity not equal,order no: %s, name: %s, export quantity: %s, buy no: %s %n", exportOrderDetail.getOrderNo(), exportOrderDetail.getProductName(), exportOrderDetail.getQuantity(), purchaseDetail.getSignNumber());
                                exportOrderDetail.setBuyAmount(purchaseDetail.getPurchasePriceCNY().multiply(exportOrderDetail.getQuantity()));
                                exportOrderDetail.setTaxRefund(exportOrderDetail.getBuyAmount().multiply(new BigDecimal("0.115")).setScale(4, RoundingMode.HALF_UP));
                                exportOrderDetail.setAfterTaxAmount(exportOrderDetail.getBuyAmount().multiply(new BigDecimal("0.885")).setScale(4, RoundingMode.HALF_UP));

                            }
                        }
                        if (isNotTaxRefund(exportOrderDetail)) {
                            exportOrderDetail.setTaxRefund(BigDecimal.ZERO);
                            exportOrderDetail.setAfterTaxAmount(purchaseDetail.getPurchaseAmountCNY());
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isNotTaxRefund(ExportOrderDetail orderDetail) {
        if(orderDetail.getOrderNo().contains("FTM2511024")
            || orderDetail.getOrderNo().contains("FTM2510012")) {
            return true;
        }
        return notTaxRefundList.contains(orderDetail);
    }

    private static void initPurchaseDetailMap(List<PurchaseDetail> purchaseDetails) {
        for (PurchaseDetail purchaseDetail : purchaseDetails) {
            if (StringUtil.isNotBlank(purchaseDetail.getPurchaseContractNo())) {
                PurchaseDetailKey purchaseDetailKey = new PurchaseDetailKey(purchaseDetail.getPurchaseContractNo(), purchaseDetail.getChineseName());
                purchaseDetailMap.put(purchaseDetailKey, purchaseDetail);
            }
            if (StringUtil.isNotBlank(purchaseDetail.getSalesContractNo())) {
                PurchaseDetailKey purchaseDetailKey = new PurchaseDetailKey(purchaseDetail.getSalesContractNo(), purchaseDetail.getChineseName());
                purchaseDetailMap.put(purchaseDetailKey, purchaseDetail);
            }
        }
    }

    private static void fillOrderDetailList(List<OrderDetail> orderDetails) throws DocumentException, FileNotFoundException {
        XmlParser xmlParser = new XmlParser();
        List<Order> orders = xmlParser.getOrderList("data/xml/2025");
        BigDecimal total = BigDecimal.ZERO;
        for (Order order : orders) {
            if ("FOB".equalsIgnoreCase(order.getCjType())) {
                BigDecimal currencyRate = getCurrencyRate(order.getTransportDate(), order.getCurrency());
                order.setOffAmountCNY(order.getTotalAmount().multiply(currencyRate).setScale(4, RoundingMode.HALF_UP));
                order.setGoodsAmount(order.getTotalAmount());
                order.setGoodsAmountCNY(order.getGoodsAmount().multiply(currencyRate).setScale(4, RoundingMode.HALF_UP));
                order.setCurrencyRate(currencyRate);
                order.getOrderDetails().forEach(detail -> {
                    detail.setInvoiceAmountCNY(detail.getAmount().multiply(currencyRate).setScale(4, RoundingMode.HALF_UP));
                    detail.setInvoicePriceCNY(detail.getAmount().divide(detail.getUnit(), 12, RoundingMode.HALF_UP));
                });


            } else if ("CIF".equalsIgnoreCase(order.getCjType())) {
                try {
                    BigDecimal allFeeCNY = order.getYfAmount().multiply(getCurrencyRate(order.getTransportDate(), order.getYfCurrency())).add(order.getBfAmount().multiply(getCurrencyRate(order.getTransportDate(), order.getBfCurrency()))).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal currencyRate = getCurrencyRate(order.getTransportDate(), order.getCurrency());
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
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if ("C&F".equalsIgnoreCase(order.getCjType())) {
                BigDecimal allFeeCNY = order.getYfAmount().multiply(getCurrencyRate(order.getTransportDate(), order.getYfCurrency())).setScale(2, RoundingMode.HALF_UP);
                BigDecimal currencyRate = getCurrencyRate(order.getTransportDate(), order.getCurrency());
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
                    orderDetail.setInvoiceAmountCNY(orderDetail.getInvoiceAmountCNY().setScale(2, RoundingMode.HALF_UP));
                }
            } else {
                System.err.printf("unknown CJtype: %s, %s", order.getOrderNo(), order.getCjType());
                System.out.println();
                System.out.println();
            }

            for (OrderDetail orderDetail : order.getOrderDetails()) {
                orderDetail.setOrderId(order.getOrderId());
                orderDetail.setOrderNo(order.getOrderNo());
                orderDetail.setTransportDate(order.getTransportDate());
                total = total.add(orderDetail.getInvoiceAmountCNY());
                orderDetails.add(orderDetail);
            }
        }

        System.out.println("total = " + total);
    }

    private static BigDecimal getCurrencyRate(String date, String currency) {
        String currencyKey = currency + "_" + date.substring(0, 6);
        return rateMap.get(currencyKey);
    }

    private static void initRateMap() {
        //美元
        rateMap.put("USD_202501", BigDecimal.valueOf(7.2786));
        rateMap.put("USD_202502", BigDecimal.valueOf(7.2558));
        rateMap.put("USD_202503", BigDecimal.valueOf(7.2627));
        rateMap.put("USD_202504", BigDecimal.valueOf(7.2575));
        rateMap.put("USD_202505", BigDecimal.valueOf(7.2498));
        rateMap.put("USD_202506", BigDecimal.valueOf(7.1830));
        rateMap.put("USD_202507", BigDecimal.valueOf(7.1511));
        rateMap.put("USD_202508", BigDecimal.valueOf(7.1687));
        rateMap.put("USD_202509", BigDecimal.valueOf(7.1248));
        rateMap.put("USD_202510", BigDecimal.valueOf(7.1037));
        rateMap.put("USD_202511", BigDecimal.valueOf(7.1027));
        rateMap.put("USD_202512", BigDecimal.valueOf(7.0556));

        //欧元
        rateMap.put("EUR_202501", BigDecimal.valueOf(7.5326));
        rateMap.put("EUR_202502", BigDecimal.valueOf(7.5104));
        rateMap.put("EUR_202503", BigDecimal.valueOf(7.5199));
        rateMap.put("EUR_202504", BigDecimal.valueOf(7.8133));
        rateMap.put("EUR_202505", BigDecimal.valueOf(8.1980));
        rateMap.put("EUR_202506", BigDecimal.valueOf(8.1426));
        rateMap.put("EUR_202507", BigDecimal.valueOf(8.4202));
        rateMap.put("EUR_202508", BigDecimal.valueOf(8.2779));
        rateMap.put("EUR_202509", BigDecimal.valueOf(8.3245));
        rateMap.put("EUR_202510", BigDecimal.valueOf(8.3386));
        rateMap.put("EUR_202511", BigDecimal.valueOf(8.1828));
        rateMap.put("EUR_202512", BigDecimal.valueOf(8.2021));

        //澳大利亚元
        rateMap.put("AUD_202501", BigDecimal.valueOf(4.5017));
        rateMap.put("AUD_202502", BigDecimal.valueOf(4.5064));
        rateMap.put("AUD_202503", BigDecimal.valueOf(4.4991));
        rateMap.put("AUD_202504", BigDecimal.valueOf(4.5305));
        rateMap.put("AUD_202505", BigDecimal.valueOf(4.6280));
        rateMap.put("AUD_202506", BigDecimal.valueOf(4.6162));
        rateMap.put("AUD_202507", BigDecimal.valueOf(4.6874));
        rateMap.put("AUD_202508", BigDecimal.valueOf(4.6256));
        rateMap.put("AUD_202509", BigDecimal.valueOf(4.6558));
        rateMap.put("AUD_202510", BigDecimal.valueOf(4.6943));
        rateMap.put("AUD_202511", BigDecimal.valueOf(4.6674));
        rateMap.put("AUD_202512", BigDecimal.valueOf(4.6211));


        //人民币
        rateMap.put("CNY_202501", BigDecimal.ONE);
        rateMap.put("CNY_202502", BigDecimal.ONE);
        rateMap.put("CNY_202503", BigDecimal.ONE);
        rateMap.put("CNY_202504", BigDecimal.ONE);
        rateMap.put("CNY_202505", BigDecimal.ONE);
        rateMap.put("CNY_202506", BigDecimal.ONE);
        rateMap.put("CNY_202507", BigDecimal.ONE);
        rateMap.put("CNY_202508", BigDecimal.ONE);
        rateMap.put("CNY_202509", BigDecimal.ONE);
        rateMap.put("CNY_202510", BigDecimal.ONE);
        rateMap.put("CNY_202511", BigDecimal.ONE);
        rateMap.put("CNY_202512", BigDecimal.ONE);
    }
}
