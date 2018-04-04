package com.buzz.test.pattern.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoisonPotion implements Potion {
    private Logger logger = LoggerFactory.getLogger(PoisonPotion.class);

    @Override
    public void drink() {
        logger.info("Urgh! This is poisonous. (Potion={})", System.identityHashCode(this));

    }
}
