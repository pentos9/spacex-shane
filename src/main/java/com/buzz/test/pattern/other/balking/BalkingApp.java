package com.buzz.test.pattern.other.balking;

import jdk.management.resource.internal.inst.WindowsAsynchronousServerSocketChannelImplRMHooks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BalkingApp {

    private static Logger logger = LoggerFactory.getLogger(BalkingApp.class);

    public static void main(String[] args) {
        final WashingMachine washingMachine = new WashingMachine();
        final int threadNumber = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadNumber; i++) {
            executorService.execute(washingMachine::wash);
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("BalkingApp#ERROR: waiting on executor service shutdown");
        }

    }
}
