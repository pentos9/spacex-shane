package com.buzz.test.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueApp {

    private static final Logger logger = LoggerFactory.getLogger(BlockingQueueApp.class);

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        final int queueSize = 1024;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(queueSize);

        Thread producer = new Thread(() -> {
            try {

                do {
                    String item = String.valueOf(new Random().nextInt());
                    blockingQueue.put(item);
                    logger.info(String.format("producer put:%s,size:%s", item, blockingQueue.size()));
                } while (blockingQueue.size() < queueSize);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Runnable consumerTask = () -> {
            try {
                do {
                    String item = blockingQueue.take();
                    logger.info(String.format("consumer take:%s", item));

                } while (blockingQueue.size() > 0);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread consumer = new Thread(consumerTask);

        producer.start();
        consumer.start();
    }
}
