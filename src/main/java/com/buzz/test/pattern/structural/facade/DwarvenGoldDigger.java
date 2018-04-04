package com.buzz.test.pattern.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwarvenGoldDigger extends DwarvenMineWorker {

    private static Logger logger = LoggerFactory.getLogger(DwarvenTunnelDigger.class);

    @Override
    public void work() {
        logger.info("{} digs for gold.", name());

    }

    @Override
    public String name() {
        return "Dwarven Gold Digger";
    }
}
