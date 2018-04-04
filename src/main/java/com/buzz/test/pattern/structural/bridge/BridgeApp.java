package com.buzz.test.pattern.structural.bridge;

public class BridgeApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Sword sword = new Sword(new SoulEatingEnchantment());
        sword.wield();
        sword.swing();
        sword.unwield();

        Hammer hammer = new Hammer(new FlyingEnchantment());
        hammer.wield();
        hammer.swing();
        hammer.unwield();
    }
}
