package com.buzz.test.core.concurrent.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithCondition {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockWithCondition.class);

    private Stack<String> stack = new Stack<>();

    private static final int CAPACITY = 5;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition stackEmptyCondition = reentrantLock.newCondition();

    private Condition stackFullCondition = reentrantLock.newCondition();

    private void pushToStack(String item) throws InterruptedException {
        try {
            reentrantLock.lock();
            if (stack.size() == CAPACITY) {
                logger.info("stack is full now,thread name:{}", Thread.currentThread().getName());
                stackFullCondition.await();
            }

            logger.info("Pushing the item:{}", item);
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    private String popFromStack() throws InterruptedException {
        try {
            reentrantLock.lock();
            if (stack.size() == 0) {
                logger.info("stack is empty,thread name:{}", Thread.currentThread().getName());
                stackEmptyCondition.await();
            }

            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {
        final int threadCount = 2;

        ReentrantLockWithCondition object = new ReentrantLockWithCondition();
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);

        service.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    object.pushToStack("item" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        service.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    logger.info("Item popped:" + object.popFromStack());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.shutdown();

    }

}
