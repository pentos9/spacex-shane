package com.buzz.test.pattern.structural.adaptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishingBoat {
    private Logger logger = LoggerFactory.getLogger(FishingBoat.class);

    public void sail() {
        logger.info("the fishing boat is sailing.");
    }
}
