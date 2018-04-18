package com.buzz.test.pattern.other.marker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Thief {
    private Logger logger = LoggerFactory.getLogger(Thief.class);

    public void steal() {
        logger.info("steal valuable items for fun");
    }

    public void doNothing() {
        logger.info("pretend nothing happened and just leave");
    }
}
