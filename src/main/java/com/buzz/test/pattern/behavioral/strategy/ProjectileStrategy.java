package com.buzz.test.pattern.behavioral.strategy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectileStrategy implements DragonSlayingStrategy {

    private Logger logger = LoggerFactory.getLogger(ProjectileStrategy.class);

    @Override
    public void execute() {
        logger.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!");
    }
}
