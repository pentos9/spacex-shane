package com.buzz.test;

import sun.misc.Unsafe;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lucas on 2/5/18.
 */
public class TestDemoApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque(10);
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        CyclicBarrier barrier = new CyclicBarrier(4);
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TreeMap treeMap = new TreeMap();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        String s = new String();

        Thread thread = new Thread(() -> {
            System.out.println("start test...");
        });

        AtomicInteger integer = new AtomicInteger(0);
        integer.incrementAndGet();//Unsafe.getAndAddInt()->compareAndSet()
        Unsafe unsafe = Unsafe.getUnsafe();

        thread.start();

        run();


        Class clazz = String.class;

    }

    public static void run() {
        JVMInfos();
        JVMDetails();
        test();
    }

    public static void JVMInfos() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemory = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemory = memoryMXBean.getNonHeapMemoryUsage();

        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        System.out.println("maxMemory:" + (maxMemory >> 20) + "(MB)");

        System.out.println("heapMemory:" + (heapMemory.getInit() >> 20) + "(MB)");
        System.out.println("nonHeapMemory:" + (nonHeapMemory.getInit() >> 20) + "(MB)");
        System.out.println("heapMemory:" + heapMemory);
        System.out.println("nonHeapMemory:" + nonHeapMemory);

    }

    public static void JVMDetails() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("freeMemory:" + (runtime.freeMemory() >> 20));//JVM可用但是没有用的那部分内存
        System.out.println("maxMemory:" + (runtime.maxMemory() >> 20));//JVM最大可用内存
        System.out.println("totalMemory:" + (runtime.totalMemory() >> 20));//实际使用内存

    }

    public synchronized static void test() {
        Runnable calTask = () -> {
            int random = new Random().nextInt();
            int result = Math.abs(random);
            System.out.println(result);
        };
        final int threadCount = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i <= threadCount; i++) {
            executorService.submit(calTask);
        }
        executorService.shutdown();
    }

    public void add() {
        int i = 0;
        i++;
    }
}
