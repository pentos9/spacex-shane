package com.buzz.test.pattern.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClubbedTroll implements Troll {

    private Logger logger = LoggerFactory.getLogger(ClubbedTroll.class);

    private Troll decorated;

    public ClubbedTroll(Troll decorated) {
        this.decorated = decorated;
    }

    @Override
    public void attack() {
        decorated.attack();
        logger.info("the troll swings at you with a club!");
    }

    @Override
    public int getAttack() {
        return decorated.getAttack() + 10;
    }

    @Override
    public void fleeBattle() {
        decorated.fleeBattle();
    }
}
