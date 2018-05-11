package com.buzz.common.cache;


public class KeyGenerator {
    private static final String SEPARATOR = ":";

    private KeyGenerator() {
    }

    public static String getKey(String prefix, String text) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix).append(SEPARATOR).append(text);
        return builder.toString();
    }

    public static String getKey(String aText, String bText, String cText) {
        StringBuilder builder = new StringBuilder();
        builder.append(aText).append(SEPARATOR).append(bText).append(SEPARATOR).append(cText);
        return builder.toString();
    }

    public static String getKey(String[] items) {
        StringBuilder builder = new StringBuilder();
        int length = items.length;
        for (int i = 0; i < length - 1; i++) {
            builder.append(items[i]);
            builder.append(SEPARATOR);
        }
        builder.append(items[length - 1]);
        return builder.toString();
    }

    public static String[] split(String key) {
        return key.split(SEPARATOR);
    }
}
