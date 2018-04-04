package com.buzz.test.pattern.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HolyWaterPotion implements Potion {

    private Logger logger = LoggerFactory.getLogger(HolyWaterPotion.class);

    @Override
    public void drink() {
        logger.info("You feel blessed. (Potion={})", System.identityHashCode(this));
    }
}
