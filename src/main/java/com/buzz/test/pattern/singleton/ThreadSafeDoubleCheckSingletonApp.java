package com.buzz.test.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeDoubleCheckSingletonApp {

    private static Logger logger = LoggerFactory.getLogger(ThreadSafeDoubleCheckSingletonApp.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final int loopTimes = 100;
        for (int i = 0; i < loopTimes; i++) {
            executorService.submit(() -> {
                ThreadSafeDoubleCheckSingleton singleton =
                        ThreadSafeDoubleCheckSingleton.getInstance();

                logger.info(String.format("DCL->%s", singleton.hashCode()));

            });
        }


        for (int i = 0; i < loopTimes; i++) {
            executorService.submit(() -> {
                UnThreadSafeSingleton unthreadSafeSingle = UnThreadSafeSingleton.getInstance();
                logger.info(String.format("UnThreadSafe->: %s", unthreadSafeSingle.hashCode()));

            });
        }

        executorService.shutdown();

    }
}
