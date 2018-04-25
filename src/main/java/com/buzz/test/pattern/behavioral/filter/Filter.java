package com.buzz.test.pattern.behavioral.filter;

public interface Filter {
    String executor(Order order);

    void setNext(Filter filter);

    Filter getNext();

    Filter getLast();
}
