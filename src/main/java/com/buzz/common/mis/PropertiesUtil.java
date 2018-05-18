package com.buzz.common.mis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    public static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static final String getProperty(String key) {
        return System.getProperty(key);
    }

    public static final String getValueByKey(String filePath, String key) {
        Properties properties = new Properties();

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(in);
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final Map<String, String> properties(InputStream in) {
        Map<String, String> map = new HashMap<>();
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration en = properties.propertyNames();
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = properties.getProperty(strKey);
            map.put(strKey, strValue);
        }
        return map;
    }

    public static final Map<String, String> getAllProperties(String filePath) {
        Map<String, String> map = new HashMap<>();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            return properties(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static final void writeProperties(String filePath, String pKey, String pValue) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
            OutputStream fos = new FileOutputStream(filePath);
            properties.setProperty(pKey, pValue);
            properties.store(fos, "Update '" + pKey + "' value");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
