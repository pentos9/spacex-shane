package com.buzz.test.pattern.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwarvenTunnelDigger extends DwarvenMineWorker {

    private static Logger logger = LoggerFactory.getLogger(DwarvenTunnelDigger.class);

    @Override
    public void work() {
        logger.info("{} creates another promising tunnel.", name());
    }

    @Override
    public String name() {
        return "Dwarven Tunnel Digger";
    }
}
