package com.buzz.common.codec;

import java.util.Random;

public class SaltUtil {
    private static final int MAX = 99999999;

    public static String generateSalt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(random.nextInt(MAX)).append(random.nextInt(MAX));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");//高位补0
            }
        }
        return sb.toString();
    }
}
