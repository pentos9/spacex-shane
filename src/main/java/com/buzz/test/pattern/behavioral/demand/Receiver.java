package com.buzz.test.pattern.behavioral.demand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Receiver {

    private Logger logger = LoggerFactory.getLogger(Receiver.class);

    public void action() {
        logger.info("execute...");
    }
}
