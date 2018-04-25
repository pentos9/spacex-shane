package com.buzz.test.pattern.behavioral.filter;

public class NameFilter extends AbstractFilter {
    @Override
    public String executor(Order order) {
        String result = super.executor(order);
        if (order.getName() == null || order.getName().isEmpty() || order.getName().matches(".*[^\\w|\\s]+.*")) {
            return result + "invalid name";
        } else {
            return result;
        }
    }
}
