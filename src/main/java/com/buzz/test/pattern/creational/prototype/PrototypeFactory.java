package com.buzz.test.pattern.creational.prototype;


public class PrototypeFactory {

    public static Device getInstance(AppleDevice type) throws CloneNotSupportedException {
        switch (type) {
            case IPHONE:
                return new Phone("iphoneX", Color.BLACK).clone();
            case PAD:
                return new Pad("pad", Color.BLACK).clone();
            case MACBOOK:
                return new MacBook("macbook pro 2015", Color.BLACK);
            default:
                throw new RuntimeException("Unsupported type!");

        }
    }
}
