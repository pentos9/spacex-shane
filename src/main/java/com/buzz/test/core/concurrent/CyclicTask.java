package com.buzz.test.core.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.HTMLDocument;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(CyclicTask.class);

    private CyclicBarrier cyclicBarrier;


    public CyclicTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread:" + Thread.currentThread().getName() + " is waiting...");
            cyclicBarrier.await();
            System.out.println("Thread:" + Thread.currentThread().getName() + " is released");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
