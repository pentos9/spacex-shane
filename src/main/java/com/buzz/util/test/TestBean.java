package com.buzz.util.test;

import com.buzz.util.annotation.Action;
import com.buzz.util.annotation.Bean;
import com.buzz.util.annotation.Service;

@Bean
public class TestBean {
    static {
        System.out.println("TestBean init...");
    }
}
