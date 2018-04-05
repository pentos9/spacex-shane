package com.buzz.test.pattern.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpellStrategy implements DragonSlayingStrategy {

    private Logger logger = LoggerFactory.getLogger(SpellStrategy.class);

    @Override
    public void execute() {
        logger.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!");
    }
}
