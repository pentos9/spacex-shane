package com.buzz.test.core.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class AtomicTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        LongAdder adder = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 1000).forEach(i -> {
            System.out.println(atomicInteger.get());
            executorService.submit(atomicInteger::incrementAndGet);
        });

        LongStream.range(0, 1000).forEach(i -> {
            System.out.println(i);
            executorService.submit(adder::increment);
        });

        executorService.shutdown();
    }
}
