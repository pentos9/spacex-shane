package com.buzz.test.pattern.behavioral.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubtleMethod extends StealingMethod {

    private Logger logger = LoggerFactory.getLogger(SubtleMethod.class);

    @Override
    protected String pickTarget() {
        return "shop keeper";
    }

    @Override
    protected void confuseTarget(String target) {
        logger.info("Approach the {} with tears running and hug him", target);
    }

    @Override
    protected void stealItemTarget(String target) {
        logger.info("while in close contact grab the {}'s wallet.", target);
    }
}
