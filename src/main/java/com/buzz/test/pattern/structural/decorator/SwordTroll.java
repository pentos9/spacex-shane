package com.buzz.test.pattern.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwordTroll implements Troll {

    private static Logger logger = LoggerFactory.getLogger(SwordTroll.class);

    private Troll decorated;

    public SwordTroll(Troll decorated) {
        this.decorated = decorated;
    }

    @Override
    public void attack() {
        decorated.attack();
        logger.info("the troll attacks at you with sword! It's dangerous!");

    }

    @Override
    public int getAttack() {
        return decorated.getAttack() + 20;
    }

    @Override
    public void fleeBattle() {
        decorated.fleeBattle();
    }
}
