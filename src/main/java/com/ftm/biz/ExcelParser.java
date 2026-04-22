package com.ftm.biz;

import com.ftm.pojo.Purchase;
import com.ftm.pojo.PurchaseDetail;
import com.ftm.pojo.TaxRefund;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;
import com.google.gson.Gson;

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

        initAddtionMap(nameCodeMap);
        return nameCodeMap;
    }

    public List<PurchaseDetail> parsePurchase(String fileName) throws Excel4JException, IOException {
        Map<String, Purchase> map = new HashMap<>();
        List<Purchase> purchases = ExcelUtils.getInstance().readExcel2Objects(fileName, Purchase.class, 1);
        for (Purchase purchase : purchases) {
            try {
                purchase.calcRate();
                map.put(purchase.getPurchaseContractNo(), purchase);
                purchase.setPurchaseAmountCNY(purchase.getPurchaseAmount().multiply(purchase.getPurchaseCurrencyRate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<PurchaseDetail> purchaseDetails = ExcelUtils.getInstance().readExcel2Objects(fileName, PurchaseDetail.class, 2);
        for (PurchaseDetail purchaseDetail : purchaseDetails) {
            try {
                //特殊处理
                if (map.get(purchaseDetail.getPurchaseContractNo()) != null) {



                    Purchase purchase = map.get(purchaseDetail.getPurchaseContractNo());
                    purchaseDetail.setPurchaseAmountCNY(purchase.getPurchaseCurrencyRate().multiply(purchaseDetail.getPurchaseAmount()));
                    purchaseDetail.setPurchasePriceCNY(purchase.getPurchaseCurrencyRate().multiply(purchaseDetail.getPurchasePrice()));
                    purchaseDetail.setPurchaseCurrency(purchase.getPurchaseCurrency());
                    purchaseDetail.setPurchaseCurrencyRate(purchase.getPurchaseCurrencyRate());
                    purchaseDetail.setBuyer(purchase.getPurchasePerson());
                    purchaseDetail.setSupplier(purchase.getSupplier());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return purchaseDetails;
    }

    private void initAddtionMap(Map<String, String> nameCodeMap) {
        nameCodeMap.put("藤黄果提取物", "C-0119");
        nameCodeMap.put("石杉碱甲1%", "C-0163");
        nameCodeMap.put("石杉碱甲 1%", "C-0163");
        nameCodeMap.put("一水肌酸", "A-0014");
        nameCodeMap.put("VE软胶囊", "H-0010");
        nameCodeMap.put("槐米提取物", "C-0233");
        nameCodeMap.put("D-甘露糖", "G-0051");
        nameCodeMap.put("L-瓜氨酸 DL-苹果酸", "A-0050");
        nameCodeMap.put("杜松子提取物", "C-0170");
        nameCodeMap.put("L-精氨酸α-酮戊二酸", "A-0038");
        nameCodeMap.put("苦橙提取物", "C-0068");
        nameCodeMap.put("甘氨酸锌", "A-0147");
        nameCodeMap.put("大豆磷脂", "G-0033");
        nameCodeMap.put("鱼油粉", "G-0031");
        nameCodeMap.put("叶黄素咀嚼软胶囊", "H-0004");
        nameCodeMap.put("乙酰壳糖胺", "E-0016");
        nameCodeMap.put("水飞蓟籽油微囊粉", "G-0036");
        nameCodeMap.put("甘油磷酰胆碱", "F-0102");
        nameCodeMap.put("VE 软胶囊", "H-0033");
        nameCodeMap.put("明胶", "G-0022");
        nameCodeMap.put("L-精氨酸-α-酮戊二酸盐", "A-0038");
        nameCodeMap.put("姜黄素", "C-0087");
        nameCodeMap.put("维生素 D3 & VK2 软胶囊", "H-0012");
        nameCodeMap.put("复合矿物质-Min", "I-0003");
        nameCodeMap.put("维生素D3软胶囊", "H-0014");
        nameCodeMap.put("彩盒", "J-0013");
        nameCodeMap.put("复合鱼油软胶囊", "H-0032");
        nameCodeMap.put("还原型谷胱甘肽", "F-0017");

    }
}
