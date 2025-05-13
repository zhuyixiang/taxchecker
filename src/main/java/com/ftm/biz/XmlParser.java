package com.ftm.biz;

import com.ftm.pojo.Order;
import com.ftm.pojo.OrderDetail;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public List<OrderDetail> getOrderList() throws DocumentException, FileNotFoundException {
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        File file = new File("data/xml");
        for (File dir : file.listFiles()) {
            if (dir.isDirectory()) {
                for (File xmlFile : dir.listFiles()) {
                    if (xmlFile.getName().endsWith(".xml") && xmlFile.getName().contains("解密")) {
                        try {

                            InputStream is = new FileInputStream(xmlFile.getAbsolutePath());
                            SAXReader reader = new SAXReader();
                            Document doc = reader.read(is);
                            Element root = doc.getRootElement();
                            Element dec = root.element("Dec");
                            Element header = dec.element("DecHead");
                            String orderNo = header.elementTextTrim("bgd_no");
                            String orderId = header.elementTextTrim("ht_no");
                            String transportDate = header.elementTextTrim("lj_date");
                            Element lists = dec.element("DecLists");
                            for (Element decList : lists.elements()) {
                                OrderDetail orderDetail = new OrderDetail();
                                orderDetail.setOrderNo(orderNo);
                                orderDetail.setOrderId(orderId);
                                orderDetail.setTransportDate(transportDate);
                                orderDetail.setProductCode(decList.elementTextTrim("cmcode"));
                                orderDetail.setProductName(decList.elementTextTrim("cm_name"));
                                orderDetail.setXuhao(decList.elementTextTrim("spxh"));
                                orderDetail.setCurrency(decList.elementTextTrim("Yb_bz"));
                                orderDetail.setAmount(decList.elementTextTrim("yb_amt"));
                                orderDetails.add(orderDetail);
                            }
                        }catch (Exception e) {
                            System.err.println(xmlFile.getAbsolutePath());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return orderDetails;
    }
}
