package com.buzz.test.core.hash;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HashFunctionTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String rawContent = "can you feel the love tonight?";
        String hashing = Hashing.murmur3_32().hashBytes(rawContent.getBytes()).toString();
        System.out.println(hashing);

        hashing = Hashing.sha256().hashString(rawContent, StandardCharsets.UTF_8).toString();
        System.out.println(hashing);
        Map map = new HashMap<>();
        System.out.println(0x0 >>> 16);
    }
}
