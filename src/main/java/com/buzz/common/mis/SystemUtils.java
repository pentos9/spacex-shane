package com.buzz.common.mis;

public class SystemUtils {
    private SystemUtils() {
    }

    public static String get(String key) {
        return System.getProperty(key);
    }
}
