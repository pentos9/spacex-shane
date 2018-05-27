package com.buzz.test.core.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public void start() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("All previous task are completed.");
        });

        Thread thread1 = new Thread(new CyclicTask(cyclicBarrier), "Worker1");
        Thread thread2 = new Thread(new CyclicTask(cyclicBarrier), "Worker2");
        Thread thread3 = new Thread(new CyclicTask(cyclicBarrier), "Worker3");

        if (!cyclicBarrier.isBroken()) {
            thread1.start();
            thread2.start();
            thread3.start();
        }

    }

    public static void main(String[] args) {
        new CyclicBarrierTest().start();
    }
}
