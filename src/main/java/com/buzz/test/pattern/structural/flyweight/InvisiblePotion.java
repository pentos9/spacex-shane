package com.buzz.test.pattern.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvisiblePotion implements Potion {

    private Logger logger = LoggerFactory.getLogger(InvisiblePotion.class);

    @Override
    public void drink() {
        logger.info("You become invisible.(Potion={})", System.identityHashCode(this));
    }
}
