package com.ftm.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class ConfigUtils {
    private static Properties properties = new Properties();

    public static void init() {
        try {
            String f = "config.properties";
            properties.load(new java.io.FileInputStream(f));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BigDecimal getUsdRate() {
        String value = properties.getProperty("usdRate", "7.15");
        return BigDecimal.valueOf(Double.parseDouble(value));
    }

    public static BigDecimal getAudRate() {
        String value = properties.getProperty("audRate", "4.53");
        return BigDecimal.valueOf(Double.parseDouble(value));
    }
    public static BigDecimal getEurRate() {
        String value = properties.getProperty("eurRate", "7.53");
        return BigDecimal.valueOf(Double.parseDouble(value));
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getWorkDir() {
        String value = properties.getProperty("workDir", "");
        return value != null ? value + "\\" : "";
    }

    public static String getStockWorkDir() {
        String value = properties.getProperty("stockWorkDir", "");
        return value != null ? value + "\\" : "";
    }
}
