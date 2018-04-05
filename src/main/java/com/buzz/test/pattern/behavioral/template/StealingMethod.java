package com.buzz.test.pattern.behavioral.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StealingMethod {
    private Logger logger = LoggerFactory.getLogger(StealingMethod.class);

    protected abstract String pickTarget();

    protected abstract void confuseTarget(String target);

    protected abstract void stealItemTarget(String target);

    public void steal() {
        String target = pickTarget();
        logger.info("target has been chosen as {}.", target);
        confuseTarget(target);
        stealItemTarget(target);
    }

}
