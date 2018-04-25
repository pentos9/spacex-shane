package com.buzz.test.pattern.behavioral.filter;


public class AddressFilter extends AbstractFilter {
    @Override
    public String executor(Order order) {
        String result = super.executor(order);
        if (order.getAddress() == null || order.getAddress().isEmpty()) {
            return result + "Invalid address! ";
        }
        return result;
    }
}
