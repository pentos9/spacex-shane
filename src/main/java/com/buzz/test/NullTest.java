package com.buzz.test;

import com.buzz.util.JsonUtil;

import java.util.Map;

public class NullTest {
    public static void main(String[] args) {
        Object object = new Object();
        String str = (String) null;
        String json = "{\"type\":\"worksDetail\",\"detailFlag\":\"0\",\"workId\":\"231312312\"}";
        Map<String, String> result = JsonUtil.fromJson("{\"type\":\"worksDetail\",\"detailFlag\":\"0\",\"workId\":\"231312312\"}", Map.class);
        System.out.println(result);
        System.out.println(" abcd ".trim());
    }
}
