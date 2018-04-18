package com.buzz.test.pattern.other.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    private final Task task;

    public Worker(Task task) {
        this.task = task;
    }

    @Override
    public void run() {

        logger.info("{} processing {}", Thread.currentThread().getName(), task.toString());

        try {
            Thread.sleep(task.getTimeMs());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
