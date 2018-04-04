package com.buzz.test.pattern.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwarvenCartOperator extends DwarvenMineWorker {

    private Logger logger = LoggerFactory.getLogger(DwarvenCartOperator.class);

    @Override
    public void work() {
        logger.info("{} operates the cart and moves gold chunks out of the mine.", name());
    }

    @Override
    public String name() {
        return "Dwarven Cart Operator";
    }
}
