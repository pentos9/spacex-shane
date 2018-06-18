package com.buzz.test.core;

import com.google.common.math.LongMath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongTest {
    private static Logger logger = LoggerFactory.getLogger(LongTest.class);

    public static void main(String[] args) {
        run();
        testOverFlow();
    }

    public static void run() {
        Long number = Long.MAX_VALUE;
        Long max = Long.MAX_VALUE;
        logger.info(String.valueOf(max));
        logger.info(String.valueOf(number + max));
    }

    public static void testOverFlow() {
        System.out.println(Long.toBinaryString(2) + " " + Long.toBinaryString(4));
        System.out.println(2 ^ 4);
        Long result = LongMath.checkedAdd(Long.MAX_VALUE, Long.MAX_VALUE);
        System.out.println(result);
    }
}
