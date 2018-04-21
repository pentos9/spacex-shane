package com.buzz.test.pattern.other.producercomsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {

    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final ItemQueue queue;

    private String name;

    public Consumer(String name, ItemQueue queue) {
        this.queue = queue;
        this.name = name;
    }

    public void consume() throws InterruptedException {
        Item item = queue.take();
        logger.info("Consumer [{}] consume item [{}] produced by [{}]", name, item.getId(), item.getProducer());
    }
}
