package com.buzz.test.pattern.behavioral.filter;

public class DepositFilter extends AbstractFilter {

    @Override
    public String executor(Order order) {
        String result = super.executor(order);
        if (order.getDepositNumber() == null || order.getDepositNumber().isEmpty()) {
            return result + " invalid deposit number";
        } else {
            return result;
        }
    }
}
