package com.buzz.test.pattern.structural.flyweight;

public class FlyweightApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        AlchemistShop shop = new AlchemistShop();
        shop.enumerate();
    }
}
