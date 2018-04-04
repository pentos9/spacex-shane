package com.buzz.test.pattern.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealingPotion implements Potion {

    private Logger logger = LoggerFactory.getLogger(HealingPotion.class);

    @Override
    public void drink() {
        logger.info("You feel healed. (Potion={})", System.identityHashCode(this));
    }
}
