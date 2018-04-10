package com.buzz.test.pattern.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeleeStrategy implements DragonSlayingStrategy {

    private Logger logger = LoggerFactory.getLogger(MeleeStrategy.class);

    @Override
    public void execute() {
        logger.info("With your Excalibur,you serve the dragon's head");
    }
}
