package com.buzz.test.core.concurrent.threadlocal;

import com.buzz.test.dynamic.Client;

public class SequenceCustom implements Sequence {
    private static CustomThreadLocal<Integer> numberContainer = new CustomThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceCustom();

        ClientThread clientThread1 = new ClientThread(sequence);
        ClientThread clientThread2 = new ClientThread(sequence);
        ClientThread clientThread3 = new ClientThread(sequence);

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();

    }
}
