package com.buzz.test.pattern.other.producercomsumer;

public class Item {

    private String producer;

    private int id;

    public Item(String producer, int id) {
        this.producer = producer;
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public int getId() {
        return id;
    }
}
