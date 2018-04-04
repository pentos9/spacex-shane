package com.buzz.test.pattern.structural.flyweight;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrengthPotion implements Potion {

    private Logger logger = LoggerFactory.getLogger(StrengthPotion.class);

    @Override
    public void drink() {
        logger.info("You feel strong. (Potion={})", System.identityHashCode(this));

    }
}
