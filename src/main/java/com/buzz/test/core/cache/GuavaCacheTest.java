package com.buzz.test.core.cache;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class GuavaCacheTest {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("apple", "apple");
        map.put("google", "google");
        map.put("facebook", "facebook");
        map.put("spacex", "spacex");
        map.put("amazon", "amazon");
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        LoadingCache<String, String> graphs = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return map.get(key);
            }
        });


        try {
            String result = graphs.get("google");
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
