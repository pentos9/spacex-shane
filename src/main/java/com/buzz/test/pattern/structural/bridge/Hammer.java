package com.buzz.test.pattern.structural.bridge;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hammer implements Weapon {

    private Logger logger = LoggerFactory.getLogger(Hammer.class);

    private final Enchantment enchantment;

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        logger.info("The hammer is wielded.");
        enchantment.onActivate();

    }

    @Override
    public void swing() {
        logger.info("The hammer is swinged.");
        enchantment.apply();
    }

    @Override
    public void unwield() {
        logger.info("The hammer is unwield.");
        enchantment.onDeactivate();

    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}
