package com.buzz.cache;


public class KeyGenerator {
    public static String generateKey(Number key) {
        return key.toString();
    }

    public static String generateKey(String prefix, String key) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(prefix).append(key);
        return keyBuilder.toString();
    }
}
