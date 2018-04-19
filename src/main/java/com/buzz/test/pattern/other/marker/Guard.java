package com.buzz.test.pattern.other.marker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guard implements Permission {
    private static final Logger logger = LoggerFactory.getLogger(Guard.class);

    public static void enter() {
        logger.info("you can enter.");
    }
}
