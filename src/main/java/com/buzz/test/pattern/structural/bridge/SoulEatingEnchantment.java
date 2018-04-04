package com.buzz.test.pattern.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoulEatingEnchantment implements Enchantment {
    private Logger logger = LoggerFactory.getLogger(SoulEatingEnchantment.class);

    @Override
    public void onActivate() {
        logger.info("The item spreads blood lust.");
    }

    @Override
    public void apply() {
        logger.info("the item begin to eat the soul of enemies");
    }

    @Override
    public void onDeactivate() {
        logger.info("Blood lust slowly disappears.");
    }
}
