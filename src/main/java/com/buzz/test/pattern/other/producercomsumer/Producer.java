package com.buzz.test.pattern.other.producercomsumer;

import java.util.Random;

public class Producer {
    private final ItemQueue queue;
    private final String name;
    private int itemId;

    public Producer(String name, ItemQueue queue) {
        this.queue = queue;
        this.name = name;
    }

    public void produce() throws InterruptedException {
        Item item = new Item(name, itemId++);
        queue.put(item);
        Random random = new Random();
        Thread.sleep(random.nextInt(2000));
    }
}
