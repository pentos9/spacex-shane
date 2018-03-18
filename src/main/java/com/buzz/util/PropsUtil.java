package com.buzz.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {
    public static Properties loadProperties(String path) {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;

    }

    public static String getString(Properties properties, String key) {
        if (properties == null) {
            return null;
        }
        String value = properties.getProperty(key);
        return value;
    }
}
