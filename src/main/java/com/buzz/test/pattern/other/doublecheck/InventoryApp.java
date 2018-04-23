package com.buzz.test.pattern.other.doublecheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryApp {

    private static Logger logger = LoggerFactory.getLogger(Inventory.class);

    public static void main(String[] args) {
        final Inventory inventory = new Inventory(1000);
        final int threadNumber = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < threadNumber; i++) {
            executorService.execute(() -> {
                while (inventory.addItem(new Item())) ;
            });
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.info("Error waiting for executor service shutdown.");
        }
    }
}
