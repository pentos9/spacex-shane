package com.buzz.test.core;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class NumberTest {

    private static Logger logger = LoggerFactory.getLogger(NumberTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        doSum();
    }

    public static void doSum() {
        List<Integer> numbers = Arrays.asList(1, 4, 5, 90);
        long sum = numbers.stream().mapToInt(Integer::intValue).sum();
        logger.info(String.format("sum=%s", sum));

        sum = numbers.stream().reduce(0, (x, y) -> x + y);
        logger.info(String.format("reduce sum=%s", sum));

        sum = numbers.stream().reduce(0, Integer::sum);
        logger.info(String.format("reduce Integer::sum sum=%s", sum));
    }
}
