package com.buzz.test;

/**
 * Created by lucas on 2/7/18.
 */

public class ThreadTestApp {
    //定义一个锁标记
    private static final String lock = new String("锁");

    private static class Thread1 implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("thread1开始执行了");
                System.out.println("thread1要wait了");
                try {
                    //wait()会释放锁标记
                    lock.wait();
                    //Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1继续执行");
                System.out.println("thread1执行完毕");
            }
        }
    }

    private static class Thread2 implements Runnable{
        @Override
        public void run() {
            //必须和Thread1用同一个锁标记，不然两个对象两个锁就失去意义了
            synchronized (lock){
                System.out.println("thread2开始执行了");
                System.out.println("thread2要notify了");
                //lock.notify(); //wait()方法立即释放对象监视器，notify()/notifyAll()方法则会等待线程剩余代码执行完毕才会放弃对象监视器。
                System.out.println("thread2继续执行");
                System.out.println("thread2执行完毕");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 a = new Thread1();
        Thread2 b = new Thread2();
        Thread thread1 = new Thread(a, "线程1");
        Thread thread2 = new Thread(b, "线程2");

        thread1.start();
        thread2.start();

//        try {
//            thread1.join();//
//            //thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
