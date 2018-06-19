package com.buzz.test.core.stream;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
    }
}
