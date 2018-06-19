package com.buzz.test.core.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class LongAccumulatorTest {

    private static Logger logger = LoggerFactory.getLogger(LongAccumulator.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        LongBinaryOperator op = (x, y) -> 2 * x + y;
        LongAccumulator longAccumulator = new LongAccumulator(op, 1L);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10).forEach(i -> executorService.submit(() -> longAccumulator.accumulate(i)));
        executorService.shutdown();
        logger.info("longAccumulator#getThenReset = {}", longAccumulator.getThenReset());
    }
}
