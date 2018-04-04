package com.buzz.test.pattern.creational.singleton;

public class UnThreadSafeSingleton {
    private static UnThreadSafeSingleton instance;

    private UnThreadSafeSingleton() {
    }

    public static UnThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new UnThreadSafeSingleton();
        }
        return instance;
    }
}
