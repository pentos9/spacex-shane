package com.buzz.test.pattern.behavioral.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HitAndReturnMethod extends StealingMethod {

    private Logger logger = LoggerFactory.getLogger(HitAndReturnMethod.class);

    @Override
    protected String pickTarget() {
        return "old goblin woman";
    }

    @Override
    protected void confuseTarget(String target) {
        logger.info("Approach the {} from behind.", target);
    }

    @Override
    protected void stealItemTarget(String target) {
        logger.info("grab the handbag and run away fast!", target);
    }
}
