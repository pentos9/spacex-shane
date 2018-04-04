package com.buzz.test.pattern.structural.bridge;

public interface Weapon {
    void wield();

    void swing();

    void unwield();

    Enchantment getEnchantment();

}
