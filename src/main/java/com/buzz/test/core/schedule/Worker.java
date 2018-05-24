package com.buzz.test.core.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker implements Runnable {

    private Logger logger = LoggerFactory.getLogger(Worker.class);

    private int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        logger.info(String.format("Worker%s is executing and doing job", id));
    }
}
