package com.buzz.test.core.stream;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("apple", "Steve Jobs");
        map.put("microsoft", "Bill Gates");
        map.put("amazon", "someoneOfAmazon");
        map.put("facebook", "Mark Zuckerberg");
        map.put("spacex", "Elon Musk");

        map.forEach((key, value) -> System.out.println(key + "," + value));
        map.putIfAbsent("alibaba", "Jack Ma");
        map.getOrDefault("alibaba", "Jack Ma for Default");
        System.out.println(map);

        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("spacex".equals(key)) {
                return value;
            }
            return null;
        });

        System.out.println("[Result] = " + result);

        result = map.searchValues(3, value -> {
            if ("Steve Jobs".equalsIgnoreCase(value)) {
                return value;
            }
            return null;
        });

        System.out.println("searchValues#value=" + result);
    }
}
