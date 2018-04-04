package com.buzz.test.pattern.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTroll implements Troll {

    private static Logger logger = LoggerFactory.getLogger(SimpleTroll.class);

    @Override
    public void attack() {
        logger.info("The troll tries to grab you!");
    }

    @Override
    public int getAttack() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        logger.info("The troll shrieks in horrors and runs away!");
    }
}
