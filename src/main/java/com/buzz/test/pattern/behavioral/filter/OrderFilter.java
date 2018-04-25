package com.buzz.test.pattern.behavioral.filter;


public class OrderFilter extends AbstractFilter {
    @Override
    public String executor(Order order) {
        String result = super.executor(order);
        if (order.getOrderItem() == null || order.getOrderItem().isEmpty()) {
            return result + "Invalid order! ";
        } else {
            return result;
        }
    }
}
