package com.buzz.test.pattern.other.producercomsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerApp {

    private static Logger logger = LoggerFactory.getLogger(ProducerConsumerApp.class);

    public static void main(String[] args) {
        ItemQueue queue = new ItemQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 4; i++) {
            final Producer producer = new Producer("Producer_" + i, queue);
            executorService.submit(() -> {
                while (true) {
                    producer.produce();
                }
            });
        }

        for (int i = 0; i < 6; i++) {
            final Consumer consumer = new Consumer("Consumer_", queue);
            executorService.submit(() -> {
                while (true) {
                    consumer.consume();
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            logger.error("Error waiting for ExecutorService shutdown");
        }
    }
}
