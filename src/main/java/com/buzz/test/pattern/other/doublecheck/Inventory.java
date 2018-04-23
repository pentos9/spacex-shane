package com.buzz.test.pattern.other.doublecheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
    private Logger logger = LoggerFactory.getLogger(Inventory.class);

    private final int inventorySize;

    private final List<Item> items;

    private final Lock lock;

    public Inventory(int inventorySize) {
        this.inventorySize = inventorySize;
        this.items = new ArrayList<>(inventorySize);
        lock = new ReentrantLock();
    }

    public boolean addItem(Item item) {

        if (items.size() < inventorySize) {
            lock.lock();
            try {
                items.add(item);
                logger.info("{}:items.size={},inventorySize={}", Thread.currentThread().getName(), items.size(), inventorySize);
                return true;
            } finally {
                lock.unlock();
            }

        }

        return false;
    }

    public final List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}
