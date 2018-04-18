package com.buzz.test;

import com.buzz.common.print.PrintUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAddTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        int numberOfThreads = 5;
        int numberOfIncrements = 100;

        Runnable runnable = () -> IntStream.range(0, numberOfIncrements).forEach(i -> counter.increment());

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(runnable);
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PrintUtil.print(String.valueOf(counter.sum()));
        executorService.shutdown();

    }
}
