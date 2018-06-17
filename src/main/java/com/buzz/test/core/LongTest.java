package com.buzz.test.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongTest {
    private static Logger logger = LoggerFactory.getLogger(LongTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Long number = Long.MAX_VALUE;
        Long max = Long.MAX_VALUE;
        logger.info(String.valueOf(max));
        logger.info(String.valueOf(number + max));
    }
}
