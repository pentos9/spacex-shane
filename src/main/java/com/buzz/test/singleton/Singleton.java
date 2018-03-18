package com.buzz.test.singleton;


public class Singleton {
    private static volatile Singleton instance = null;
    private static Object object = new Object();

    private Singleton() {
        System.out.println("init singleton()...");
    }

    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

    public static void test() {
        System.out.println(object.toString());
    }
}
